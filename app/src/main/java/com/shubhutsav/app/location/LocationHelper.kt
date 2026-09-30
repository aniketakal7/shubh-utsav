package com.shubhutsav.app.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
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
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean
import org.json.JSONArray
import org.json.JSONObject

/**
 * Result data holder for detected GPS location with exact village/town details
 * and geodesic link to nearest Panchang calculation reference city.
 */
data class DetectedLocation(
    val city: City,
    val distanceKm: Double,
    val latitude: Double,
    val longitude: Double,
    val detectedPlaceName: String? = null,
    val nearestReferenceCity: City? = null,
    val villageName: String? = null,
    val district: String? = null,
    val state: String? = null
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
     * Detects current device coordinates and resolves the exact village/town where the user is.
     */
    @SuppressLint("MissingPermission")
    @Suppress("DEPRECATION")
    fun detectExactLocation(
        context: Context,
        userLanguage: String = "en",
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
        // If last known location is recent (less than 30 minutes old), resolve immediately
        if (bestLocation != null && (now - bestLocation.time < 30 * 60 * 1000)) {
            resolveExactVillageAndReturn(context, bestLocation, userLanguage, onResult)
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
                    resolveExactVillageAndReturn(context, location, userLanguage, onResult)
                }
            }

            @Deprecated("Deprecated in Java")
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val timeoutRunnable = Runnable {
            if (isHandled.compareAndSet(false, true)) {
                try {
                    locationManager.removeUpdates(listener)
                } catch (_: Exception) {
                }

                if (bestLocation != null) {
                    resolveExactVillageAndReturn(context, bestLocation, userLanguage, onResult)
                } else {
                    onResult(LocationResult.Error("Could not retrieve GPS coordinates in time."))
                }
            }
        }

        mainHandler.postDelayed(timeoutRunnable, 6000L)

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
                resolveExactVillageAndReturn(context, bestLocation, userLanguage, onResult)
            } else {
                mainHandler.removeCallbacks(timeoutRunnable)
                isHandled.set(true)
                onResult(LocationResult.Error("No location provider available."))
            }
        }
    }

    /**
     * Backward-compatible overload for existing calls.
     */
    fun detectNearestCity(
        context: Context,
        onResult: (LocationResult) -> Unit
    ) {
        detectExactLocation(context, "en", onResult)
    }

    /**
     * Resolves the exact village from GPS coordinates using Android Geocoder
     * with an HTTP reverse-geocoding fallback (Nominatim) for complete reliability.
     */
    private fun resolveExactVillageAndReturn(
        context: Context,
        location: Location,
        userLanguage: String,
        onResult: (LocationResult) -> Unit
    ) {
        val (nearestRefCity, distKm) = CityRepository.findNearestCityWithDistance(location.latitude, location.longitude)

        Thread {
            var villageNameLocal: String? = null
            var villageNameEn: String? = null
            var districtLocal: String? = null
            var districtEn: String? = null
            var stateLocal: String? = null
            var stateEn: String? = null

            // 1. Try Android Geocoder with local language
            try {
                if (Geocoder.isPresent()) {
                    val localLocale = when (userLanguage) {
                        "mr" -> Locale("mr", "IN")
                        "hi" -> Locale("hi", "IN")
                        else -> Locale.ENGLISH
                    }
                    val geocoderLocal = Geocoder(context, localLocale)
                    @Suppress("DEPRECATION")
                    val addrs = geocoderLocal.getFromLocation(location.latitude, location.longitude, 1)
                    if (!addrs.isNullOrEmpty()) {
                        val addr = addrs[0]
                        villageNameLocal = extractVillageFromAddress(addr)
                        districtLocal = addr.subAdminArea ?: addr.locality
                        stateLocal = addr.adminArea
                    }

                    // Also query English locale if userLanguage is not English
                    if (userLanguage != "en") {
                        val geocoderEn = Geocoder(context, Locale.ENGLISH)
                        @Suppress("DEPRECATION")
                        val enAddrs = geocoderEn.getFromLocation(location.latitude, location.longitude, 1)
                        if (!enAddrs.isNullOrEmpty()) {
                            val enAddr = enAddrs[0]
                            villageNameEn = extractVillageFromAddress(enAddr)
                            districtEn = enAddr.subAdminArea ?: enAddr.locality
                            stateEn = enAddr.adminArea
                        }
                    } else {
                        villageNameEn = villageNameLocal
                        districtEn = districtLocal
                        stateEn = stateLocal
                    }
                }
            } catch (_: Exception) {
            }

            // 2. If no village name detected yet, query Nominatim HTTP fallback
            if (villageNameLocal == null && villageNameEn == null) {
                val nomResult = queryNominatimReverse(location.latitude, location.longitude, userLanguage)
                if (nomResult != null) {
                    villageNameLocal = nomResult.first
                    districtLocal = nomResult.second
                    stateLocal = nomResult.third
                    villageNameEn = villageNameLocal
                    districtEn = districtLocal
                    stateEn = stateLocal
                }
            }

            // Resolve final village / city representation
            val effectiveVillageEn = villageNameEn ?: villageNameLocal
            val effectiveVillageHi = villageNameLocal ?: villageNameEn

            val finalCity: City = if (!effectiveVillageEn.isNullOrBlank()) {
                CityRepository.createVillage(
                    name = effectiveVillageEn,
                    hindiName = effectiveVillageHi ?: effectiveVillageEn,
                    district = districtEn ?: districtLocal,
                    hindiDistrict = districtLocal ?: districtEn,
                    state = stateEn ?: stateLocal ?: nearestRefCity.state,
                    hindiState = stateLocal ?: stateEn ?: nearestRefCity.hindiState,
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            } else {
                // If completely unknown, use nearest reference city name with EXACT GPS coordinates
                nearestRefCity.copy(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            }

            val placeSummary = if (!effectiveVillageHi.isNullOrBlank()) {
                val dist = districtLocal ?: districtEn
                if (dist != null && !dist.equals(effectiveVillageHi, ignoreCase = true)) {
                    "$effectiveVillageHi, $dist"
                } else {
                    effectiveVillageHi
                }
            } else {
                nearestRefCity.displayName(userLanguage)
            }

            Handler(Looper.getMainLooper()).post {
                onResult(
                    LocationResult.Success(
                        DetectedLocation(
                            city = finalCity,
                            distanceKm = Math.round(distKm * 10.0) / 10.0,
                            latitude = location.latitude,
                            longitude = location.longitude,
                            detectedPlaceName = placeSummary,
                            nearestReferenceCity = nearestRefCity,
                            villageName = effectiveVillageHi ?: effectiveVillageEn,
                            district = districtLocal ?: districtEn,
                            state = stateLocal ?: stateEn
                        )
                    )
                )
            }
        }.start()
    }

    private fun isPlusCodeOrNumeric(s: String): Boolean {
        val clean = s.trim()
        if (clean.length < 2) return true
        if (clean.matches(Regex("^[0-9+\\-\\s,.]+$"))) return true
        if (clean.contains("+") && clean.length <= 12) return true
        return false
    }

    private fun extractVillageFromAddress(addr: Address): String? {
        // 1. subLocality (most specific in Indian villages / gram panchayats)
        val subLoc = addr.subLocality?.trim()
        if (!subLoc.isNullOrBlank() && !isPlusCodeOrNumeric(subLoc)) {
            return subLoc
        }
        // 2. locality (often town or village)
        val loc = addr.locality?.trim()
        if (!loc.isNullOrBlank() && !isPlusCodeOrNumeric(loc)) {
            return loc
        }
        // 3. featureName (sometimes village name)
        val feat = addr.featureName?.trim()
        if (!feat.isNullOrBlank() && !isPlusCodeOrNumeric(feat) && !feat.contains("+") && feat.length > 2) {
            return feat
        }
        // 4. subAdminArea (Taluka or district)
        val subAdmin = addr.subAdminArea?.trim()
        if (!subAdmin.isNullOrBlank() && !isPlusCodeOrNumeric(subAdmin)) {
            return subAdmin
        }
        return null
    }

    private fun queryNominatimReverse(lat: Double, lon: Double, lang: String): Triple<String?, String?, String?>? {
        var conn: HttpURLConnection? = null
        return try {
            val url = URL("https://nominatim.openstreetmap.org/reverse?lat=$lat&lon=$lon&format=json&addressdetails=1")
            conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 3500
            conn.readTimeout = 3500
            conn.setRequestProperty("User-Agent", "ShubhUtsavApp/1.3.0 (com.shubhutsav.app; Android)")
            conn.setRequestProperty("Accept-Language", "$lang,hi,en;q=0.8")
            if (conn.responseCode == 200) {
                val text = conn.inputStream.bufferedReader().use { it.readText() }
                val root = JSONObject(text)
                val addr = root.optJSONObject("address")
                val village = addr?.optString("village")?.ifBlank { null }
                    ?: addr?.optString("hamlet")?.ifBlank { null }
                    ?: addr?.optString("suburb")?.ifBlank { null }
                    ?: addr?.optString("town")?.ifBlank { null }
                    ?: addr?.optString("neighbourhood")?.ifBlank { null }
                    ?: addr?.optString("isolated_dwelling")?.ifBlank { null }
                    ?: addr?.optString("locality")?.ifBlank { null }
                    ?: root.optString("name").ifBlank { null }
                val district = addr?.optString("state_district")?.ifBlank { null }
                    ?: addr?.optString("county")?.ifBlank { null }
                    ?: addr?.optString("city")?.ifBlank { null }
                val state = addr?.optString("state")?.ifBlank { null }
                Triple(village, district, state)
            } else {
                null
            }
        } catch (_: Exception) {
            null
        } finally {
            conn?.disconnect()
        }
    }

    /**
     * Searches for villages or towns by name in India using Geocoder and Nominatim.
     */
    fun searchVillages(
        context: Context,
        query: String,
        userLanguage: String = "en",
        onResult: (List<City>) -> Unit
    ) {
        val cleanQuery = query.trim()
        if (cleanQuery.length < 2) {
            onResult(emptyList())
            return
        }

        Thread {
            val results = mutableListOf<City>()

            // 1. Try Geocoder
            try {
                if (Geocoder.isPresent()) {
                    val locale = when (userLanguage) {
                        "mr" -> Locale("mr", "IN")
                        "hi" -> Locale("hi", "IN")
                        else -> Locale.ENGLISH
                    }
                    val geocoder = Geocoder(context, locale)
                    @Suppress("DEPRECATION")
                    val addresses = geocoder.getFromLocationName("$cleanQuery, India", 6)
                    if (!addresses.isNullOrEmpty()) {
                        for (addr in addresses) {
                            val vName = extractVillageFromAddress(addr) ?: cleanQuery
                            val dist = addr.subAdminArea ?: addr.locality
                            val st = addr.adminArea ?: "India"
                            results.add(
                                CityRepository.createVillage(
                                    name = vName,
                                    hindiName = vName,
                                    district = dist,
                                    state = st,
                                    latitude = addr.latitude,
                                    longitude = addr.longitude
                                )
                            )
                        }
                    }
                }
            } catch (_: Exception) {
            }

            // 2. If Geocoder has nothing, try Nominatim
            if (results.isEmpty()) {
                var conn: HttpURLConnection? = null
                try {
                    val encoded = URLEncoder.encode("$cleanQuery India", "UTF-8")
                    val url = URL("https://nominatim.openstreetmap.org/search?q=$encoded&format=json&countrycodes=in&addressdetails=1&limit=6")
                    conn = url.openConnection() as HttpURLConnection
                    conn.connectTimeout = 4000
                    conn.readTimeout = 4000
                    conn.setRequestProperty("User-Agent", "ShubhUtsavApp/1.3.0 (com.shubhutsav.app; Android)")
                    conn.setRequestProperty("Accept-Language", "$userLanguage,hi,en;q=0.8")
                    if (conn.responseCode == 200) {
                        val text = conn.inputStream.bufferedReader().use { it.readText() }
                        val array = JSONArray(text)
                        for (i in 0 until array.length()) {
                            val item = array.getJSONObject(i)
                            val lat = item.getDouble("lat")
                            val lon = item.getDouble("lon")
                            val addr = item.optJSONObject("address")
                            val vName = addr?.optString("village")?.ifBlank { null }
                                ?: addr?.optString("hamlet")?.ifBlank { null }
                                ?: addr?.optString("town")?.ifBlank { null }
                                ?: addr?.optString("suburb")?.ifBlank { null }
                                ?: item.optString("name").ifBlank { cleanQuery }
                            val dist = addr?.optString("state_district")?.ifBlank { null }
                                ?: addr?.optString("county")?.ifBlank { null }
                            val st = addr?.optString("state")?.ifBlank { null } ?: "India"

                            results.add(
                                CityRepository.createVillage(
                                    name = vName,
                                    hindiName = vName,
                                    district = dist,
                                    state = st,
                                    latitude = lat,
                                    longitude = lon
                                )
                            )
                        }
                    }
                } catch (_: Exception) {
                } finally {
                    conn?.disconnect()
                }
            }

            Handler(Looper.getMainLooper()).post {
                onResult(results)
            }
        }.start()
    }
}
