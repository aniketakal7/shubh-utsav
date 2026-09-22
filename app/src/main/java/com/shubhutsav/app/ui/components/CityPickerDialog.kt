package com.shubhutsav.app.ui.components

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.CityRepository
import com.shubhutsav.app.location.LocationHelper
import com.shubhutsav.app.location.LocationResult
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon

@Composable
fun CityPickerDialog(
    currentCityId: String,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onCitySelected: (City) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var isDetectingLocation by remember { mutableStateOf(false) }
    var showGpsPromptDialog by remember { mutableStateOf(false) }

    fun processLocationDetection() {
        isDetectingLocation = true
        LocationHelper.detectNearestCity(context) { result ->
            isDetectingLocation = false
            when (result) {
                is LocationResult.Success -> {
                    val detected = result.detectedLocation
                    val toastMsg = when (language) {
                        "mr" -> {
                            val place = detected.detectedPlaceName?.let { "$it (जवळचे शहर: " } ?: ""
                            val suffix = if (detected.detectedPlaceName != null) ")" else ""
                            "स्थान ओळखले: $place${detected.city.name}$suffix, अंतर: ${detected.distanceKm} किमी"
                        }
                        "hi" -> {
                            val place = detected.detectedPlaceName?.let { "$it (निकटतम शहर: " } ?: ""
                            val suffix = if (detected.detectedPlaceName != null) ")" else ""
                            "स्थान पहचाना गया: $place${detected.city.hindiName}$suffix, दूरी: ${detected.distanceKm} किमी"
                        }
                        else -> {
                            val place = detected.detectedPlaceName?.let { "$it (Nearest: " } ?: ""
                            val suffix = if (detected.detectedPlaceName != null) ")" else ""
                            "Location detected: $place${detected.city.name}$suffix (${detected.distanceKm} km away)"
                        }
                    }
                    Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
                    onCitySelected(detected.city)
                    onDismiss()
                }
                is LocationResult.PermissionRequired -> {
                    val deniedMsg = when (language) {
                        "mr" -> "स्थान परवानगी आवश्यक आहे."
                        "hi" -> "स्थान अनुमति आवश्यक है।"
                        else -> "Location permission is required."
                    }
                    Toast.makeText(context, deniedMsg, Toast.LENGTH_SHORT).show()
                }
                is LocationResult.LocationDisabled -> {
                    showGpsPromptDialog = true
                }
                is LocationResult.Error -> {
                    val errorMsg = when (language) {
                        "mr" -> "स्थान शोधण्यात अडचण आली. कृपया सूचीमधून शहर निवडा."
                        "hi" -> "स्थान खोजने में असमर्थ। कृपया सूची से शहर चुनें।"
                        else -> "Could not detect GPS location. Please select manually."
                    }
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (fineGranted || coarseGranted) {
            if (!LocationHelper.isLocationEnabled(context)) {
                showGpsPromptDialog = true
            } else {
                processLocationDetection()
            }
        } else {
            val deniedMsg = when (language) {
                "mr" -> "स्थान परवानगी नाकारली. कृपया सूचीमधून शहर निवडा."
                "hi" -> "स्थान अनुमति अस्वीकृत। कृपया सूची से शहर चुनें।"
                else -> "Location permission denied. Please select city manually."
            }
            Toast.makeText(context, deniedMsg, Toast.LENGTH_SHORT).show()
        }
    }

    fun requestAutoDetect() {
        if (!LocationHelper.hasLocationPermission(context)) {
            locationPermissionLauncher.launch(LocationHelper.REQUIRED_PERMISSIONS)
        } else if (!LocationHelper.isLocationEnabled(context)) {
            showGpsPromptDialog = true
        } else {
            processLocationDetection()
        }
    }

    val filteredCities = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            CityRepository.cities
        } else {
            val q = searchQuery.trim().lowercase()
            CityRepository.cities.filter {
                it.name.lowercase().contains(q) ||
                it.hindiName.contains(q) ||
                it.state.lowercase().contains(q) ||
                it.hindiState.contains(q)
            }
        }
    }

    if (showGpsPromptDialog) {
        AlertDialog(
            onDismissRequest = { showGpsPromptDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = KesariyaSaffron
                )
            },
            title = {
                Text(
                    text = when (language) {
                        "mr" -> "स्थान सेवा (GPS) बंद आहे"
                        "hi" -> "लोकेशन सेवा (GPS) बंद है"
                        else -> "Location Services Disabled"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = when (language) {
                        "mr" -> "आपोआप अचूक पंचांग आणि मुहूर्ताचे शहर निवडण्यासाठी कृपया आपल्या फोनचे GPS/स्थान चालू करा."
                        "hi" -> "सटीक पंचांग और मुहूर्त का शहर स्वतः चुनने के लिए कृपया अपने फोन का GPS/स्थान चालू करें।"
                        else -> "Please turn on GPS/Location in your device settings to automatically detect the closest Panchang city."
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showGpsPromptDialog = false
                        LocationHelper.openLocationSettings(context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = KesariyaSaffron)
                ) {
                    Text(
                        text = when (language) {
                            "mr" -> "सेटिंग्ज उघडा"
                            "hi" -> "सेटिंग्स खोलें"
                            else -> "Open Settings"
                        },
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showGpsPromptDialog = false }) {
                    Text(
                        text = when (language) {
                            "mr" -> "रद्द करा"
                            "hi" -> "रद्द करें"
                            else -> "Cancel"
                        }
                    )
                }
            }
        )
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(26.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.82f)
                .padding(horizontal = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(KesariyaSaffron.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = KesariyaSaffron, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "आपले शहर निवडा"
                                "hi" -> "अपना शहर चुनें"
                                else -> "Select Your City"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = when (language) {
                        "mr" -> "अचूक सूर्योदय, सूर्यास्त आणि राहू काळ मुहूर्तासाठी शहर निवडा किंवा GPS वापरा:"
                        "hi" -> "सटीक सूर्योदय, सूर्यास्त और राहु काल मुहूर्त हेतु शहर चुनें या GPS प्रयोग करें:"
                        else -> "Panchang timings and Rahu Kaal will adjust to this location:"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Auto-Detect Location Card
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                    border = BorderStroke(1.dp, KesariyaSaffron.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(enabled = !isDetectingLocation) {
                            requestAutoDetect()
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(KesariyaSaffron.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isDetectingLocation) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(18.dp),
                                        color = KesariyaSaffron,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.MyLocation,
                                        contentDescription = "Auto Detect Location",
                                        tint = KesariyaSaffron,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = when (language) {
                                        "mr" -> if (isDetectingLocation) "स्थान शोधत आहे..." else "माझे स्थान आपोआप ओळखा (GPS)"
                                        "hi" -> if (isDetectingLocation) "स्थान खोज रहे हैं..." else "मेरा स्थान स्वतः पहचानें (GPS)"
                                        else -> if (isDetectingLocation) "Detecting GPS location..." else "Auto-Detect My Location (GPS)"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = when (language) {
                                        "mr" -> "GPS द्वारे जवळचे पंचांग शहर त्वरित सेट करा"
                                        "hi" -> "GPS द्वारा निकटतम पंचांग शहर तुरंत सेट करें"
                                        else -> "Instantly finds closest Panchang city via GPS"
                                    },
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.NearMe,
                            contentDescription = null,
                            tint = KesariyaSaffron,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = when (language) {
                                "mr" -> "शहर शोधा (उदा. पुणे, नाशिक, मुंबई)..."
                                "hi" -> "शहर खोजें (उदा. दिल्ली, मुंबई, काशी)..."
                                else -> "Search city (e.g. Delhi, Mumbai)..."
                            },
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = KesariyaSaffron,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = KesariyaSaffron,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // City List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredCities) { city ->
                        val isSelected = city.id == currentCityId
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f) else Color.Transparent,
                            border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)) else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable {
                                    onCitySelected(city)
                                    onDismiss()
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = if (language == "en") city.name else city.hindiName,
                                        fontSize = 15.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = if (language == "en") city.state else city.hindiState,
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                if (isSelected) {
                                    Surface(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(22.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Selected",
                                                tint = Color.White,
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
