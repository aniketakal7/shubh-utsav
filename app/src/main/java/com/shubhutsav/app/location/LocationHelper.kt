package com.shubhutsav.app.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.CityRepository
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Result data holder for detected GPS location matched to the nearest Panchang city.
 */
data class DetectedLocation(
    val city: City,
    val distanceKm: Double,
    val latitude: Double,
    val longitude: Double,
    val detectedPlaceName: String? = null
)

/**
 * Result sealed hierarchy for auto-detect operations.
 */
sealed class LocationResult {
    data class Success(val detectedLocation: DetectedLocation) : LocationResult()
    object PermissionRequired : LocationResult()
    object LocationDisabled : LocationResult()
    data class Error(val message: String) : LocationResult()
}

object LocationHelper {

    val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    /**
     * Checks if either FINE or COARSE location permission is granted.
     */
    fun hasLocationPermission(context: Context): Boolean {
        val fineGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineGranted || coarseGranted
    }

    /**
     * Checks if location services (GPS or Network provider) are enabled on the device.
     */
    fun isLocationEnabled(context: Context): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager ?: return false
        return LocationManagerCompat.isLocationEnabled(lm)
    }

    /**
     * Opens system location settings screen so user can toggle GPS on.
     */
    fun openLocationSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (_: Exception) {
            val genericIntent = Intent(Settings.ACTION_SETTINGS).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(genericIntent)
        }
    }

    /**
     * Detects current device coordinates and maps them to the nearest Panchang city.
     * Guaranteed to execute the onResult callback on the main Android UI thread.
     */
    @SuppressLint("MissingPermission")
    @Suppress("DEPRECATION")
    fun detectNearestCity(
        context: Context,
        onResult: (LocationResult) -> Unit
    ) {
        if (!hasLocationPermission(context)) {
            onResult(LocationResult.PermissionRequired)
            return
        }

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        if (locationManager == null || !LocationManagerCompat.isLocationEnabled(locationManager)) {
            onResult(LocationResult.LocationDisabled)
            return
        }

        // 1. Check last known location from all available providers
        val providers = listOf(
            LocationManager.GPS_PROVIDER,
            LocationManager.NETWORK_PROVIDER,
            LocationManager.PASSIVE_PROVIDER
        )

        var bestLocation: Location? = null
        for (provider in providers) {
            try {
                if (locationManager.isProviderEnabled(provider)) {
                    val loc = locationManager.getLastKnownLocation(provider)
                    if (loc != null) {
                        if (bestLocation == null || loc.time > bestLocation.time) {
                            bestLocation = loc
                        }
                    }
                }
            } catch (_: SecurityException) {
            } catch (_: Exception) {
            }
        }

        val now = System.currentTimeMillis()
        // If last known location is recent (less than 1 hour old), resolve immediately
        if (bestLocation != null && (now - bestLocation.time < 60 * 60 * 1000)) {
            resolveAndReturn(context, bestLocation, onResult)
            return
        }

        // 2. Request a fresh single location update with timeout
        val isHandled = AtomicBoolean(false)
        val mainHandler = Handler(Looper.getMainLooper())

        val listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (isHandled.compareAndSet(false, true)) {
                    try {
                        locationManager.removeUpdates(this)
                    } catch (_: Exception) {
                    }
                    resolveAndReturn(context, location, onResult)
                }
            }

            @Deprecated("Deprecated in Java")
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
            }
        }

        val timeoutRunnable = Runnable {
            if (isHandled.compareAndSet(false, true)) {
                try {
                    locationManager.removeUpdates(listener)
                } catch (_: Exception) {
                }

                if (bestLocation != null) {
                    resolveAndReturn(context, bestLocation, onResult)
                } else {
                    onResult(LocationResult.Error("Could not retrieve GPS coordinates in time."))
                }
            }
        }

        mainHandler.postDelayed(timeoutRunnable, 5000L)

        var requested = false
        try {
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, listener, Looper.getMainLooper())
                requested = true
            }
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, listener, Looper.getMainLooper())
                requested = true
            }
        } catch (_: Exception) {
        }

        if (!requested) {
            if (bestLocation != null) {
                mainHandler.removeCallbacks(timeoutRunnable)
                isHandled.set(true)
                resolveAndReturn(context, bestLocation, onResult)
            } else {
                mainHandler.removeCallbacks(timeoutRunnable)
                isHandled.set(true)
                onResult(LocationResult.Error("No location provider available."))
            }
        }
    }

    private fun resolveAndReturn(
        context: Context,
        location: Location,
        onResult: (LocationResult) -> Unit
    ) {
        val (city, distKm) = CityRepository.findNearestCityWithDistance(location.latitude, location.longitude)

        Thread {
            var placeName: String? = null
            try {
                if (Geocoder.isPresent()) {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    @Suppress("DEPRECATION")
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (!addresses.isNullOrEmpty()) {
                        val addr = addresses[0]
                        placeName = addr.locality ?: addr.subAdminArea ?: addr.adminArea
                    }
                }
            } catch (_: Exception) {
            }

            Handler(Looper.getMainLooper()).post {
                onResult(
                    LocationResult.Success(
                        DetectedLocation(
                            city = city,
                            distanceKm = Math.round(distKm * 10.0) / 10.0,
                            latitude = location.latitude,
                            longitude = location.longitude,
                            detectedPlaceName = placeName
                        )
                    )
                )
            }
        }.start()
    }
}
