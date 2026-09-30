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
import androidx.compose.material.icons.filled.Add
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
    currentCity: City? = null,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onCitySelected: (City) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var isDetectingLocation by remember { mutableStateOf(false) }
    var showGpsPromptDialog by remember { mutableStateOf(false) }
    var showManualVillageDialog by remember { mutableStateOf(false) }

    var isSearchingOnline by remember { mutableStateOf(false) }
    var onlineVillageResults by remember { mutableStateOf<List<City>>(emptyList()) }

    fun processLocationDetection() {
        isDetectingLocation = true
        LocationHelper.detectExactLocation(context, language) { result ->
            isDetectingLocation = false
            when (result) {
                is LocationResult.Success -> {
                    val detected = result.detectedLocation
                    val vName = detected.villageName ?: detected.city.displayName(language)
                    val distName = detected.district
                    val toastMsg = when (language) {
                        "mr" -> "अचूक गाव ओळखले: $vName${if (distName != null) " ($distName)" else ""} 🏡"
                        "hi" -> "सटीक गाँव पहचाना गया: $vName${if (distName != null) " ($distName)" else ""} 🏡"
                        else -> "Exact Village Detected: $vName${if (distName != null) " ($distName)" else ""} 🏡"
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
                        "mr" -> "स्थान शोधण्यात अडचण आली. कृपया सूचीमधून शहर निवडा किंवा गाव टाईप करा."
                        "hi" -> "स्थान खोजने में असमर्थ। कृपया सूची से चुनें या गाँव टाइप करें।"
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
                "mr" -> "स्थान परवानगी नाकारली. कृपया सूचीमधून शहर निवडा किंवा गाव टाईप करा."
                "hi" -> "स्थान अनुमति अस्वीकृत। कृपया सूची से चुनें या गाँव लिखें।"
                else -> "Location permission denied. Please select city or village manually."
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

    fun searchOnlineVillage(query: String) {
        if (query.isBlank()) return
        isSearchingOnline = true
        LocationHelper.searchVillages(context, query, language) { list ->
            isSearchingOnline = false
            onlineVillageResults = list
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

    // GPS prompt dialog
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
                        "mr" -> "आपोआप अचूक गाव आणि पंचांग मुहूर्त शोधण्यासाठी कृपया आपल्या फोनचे GPS/स्थान चालू करा."
                        "hi" -> "सटीक गाँव और पंचांग मुहूर्त स्वतः पहचानने हेतु कृपया अपने फोन का GPS/स्थान चालू करें।"
                        else -> "Please turn on GPS/Location in device settings to automatically detect your exact village coordinates."
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

    // Manual Village Entry Dialog
    if (showManualVillageDialog) {
        ManualVillageDialog(
            language = language,
            onDismiss = { showManualVillageDialog = false },
            onVillageCreated = { newVillage ->
                showManualVillageDialog = false
                onCitySelected(newVillage)
                onDismiss()
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
                .fillMaxHeight(0.85f)
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
                                "mr" -> "आपले गाव किंवा शहर निवडा"
                                "hi" -> "अपना गाँव या शहर चुनें"
                                else -> "Select Village or City"
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
                        "mr" -> "अचूक सूर्योदय, सूर्यास्त आणि पंचांग मुहूर्तासाठी आपले गाव किंवा शहर निवडा:"
                        "hi" -> "सटीक सूर्योदय, सूर्यास्त और पंचांग मुहूर्त हेतु अपना गाँव या शहर चुनें:"
                        else -> "Panchang timings and Rahu Kaal will adjust to your exact village:"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Auto-Detect Exact Village Card
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
                                        "mr" -> if (isDetectingLocation) "अचूक गाव शोधत आहे..." else "माझे अचूक गाव ओळखा (GPS) 🏡"
                                        "hi" -> if (isDetectingLocation) "सटीक गाँव खोज रहे हैं..." else "मेरा सटीक गाँव पहचानें (GPS) 🏡"
                                        else -> if (isDetectingLocation) "Detecting exact village..." else "Auto-Detect My Exact Village (GPS) 🏡"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = when (language) {
                                        "mr" -> "आपण ज्या गावात आहात ते अचूक गाव GPS द्वारे सेट करा"
                                        "hi" -> "आप जिस गाँव में हैं वह सटीक गाँव GPS द्वारा सेट करें"
                                        else -> "Finds your exact village location via GPS"
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

                Spacer(modifier = Modifier.height(10.dp))

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        if (it.length >= 3) {
                            searchOnlineVillage(it)
                        } else if (it.isBlank()) {
                            onlineVillageResults = emptyList()
                        }
                    },
                    placeholder = {
                        Text(
                            text = when (language) {
                                "mr" -> "गाव किंवा शहर शोधा (उदा. शिरवळ, बारामती)..."
                                "hi" -> "गाँव या शहर खोजें (उदा. शिरडी, बारामती)..."
                                else -> "Search village or city (e.g. Shirwal)..."
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
                            IconButton(onClick = {
                                searchQuery = ""
                                onlineVillageResults = emptyList()
                            }) {
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

                // Action row: Online search trigger & manual entry
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (searchQuery.trim().length >= 2) {
                        TextButton(
                            onClick = { searchOnlineVillage(searchQuery) },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            if (isSearchingOnline) {
                                CircularProgressIndicator(modifier = Modifier.size(12.dp), strokeWidth = 1.5.dp, color = KesariyaSaffron)
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            Text(
                                text = when (language) {
                                    "mr" -> "गाव शोधा 🔍"
                                    "hi" -> "गाँव खोजें 🔍"
                                    else -> "Search Village 🔍"
                                },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KesariyaSaffron
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    TextButton(
                        onClick = { showManualVillageDialog = true },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = KesariyaSaffron, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "गाव प्रविष्ट करा"
                                "hi" -> "गाँव प्रविष्ट करें"
                                else -> "Enter Village"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KesariyaSaffron
                        )
                    }
                }

                // Locations list
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    // Online Village Search Results
                    if (onlineVillageResults.isNotEmpty()) {
                        item {
                            Text(
                                text = when (language) {
                                    "mr" -> "शोधलेली गावे (ऑनलाइन):"
                                    "hi" -> "खोजे गए गाँव (ऑनलाइन):"
                                    else -> "Found Villages (Online):"
                                },
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = KesariyaSaffron,
                                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                            )
                        }

                        items(onlineVillageResults) { village ->
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                                border = BorderStroke(1.dp, KesariyaSaffron.copy(alpha = 0.4f)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable {
                                        onCitySelected(village)
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
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(text = "🏡", fontSize = 13.sp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = village.displayName(language),
                                                fontSize = 14.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                        Text(
                                            text = village.displaySubtext(language),
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Text(
                                        text = when (language) {
                                            "mr" -> "निवडा"
                                            "hi" -> "चुनें"
                                            else -> "Select"
                                        },
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KesariyaSaffron
                                    )
                                }
                            }
                        }

                        item {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        }
                    }

                    // Standard Cities
                    item {
                        Text(
                            text = when (language) {
                                "mr" -> "प्रमुख शहरे / संदर्भ स्थाने:"
                                "hi" -> "प्रमुख शहर / संदर्भ स्थान:"
                                else -> "Major Reference Cities:"
                            },
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                        )
                    }

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
                                        text = city.displayName(language),
                                        fontSize = 15.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = city.displaySubtext(language),
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

/**
 * Clean dialog for manually entering a village name and district.
 */
@Composable
private fun ManualVillageDialog(
    language: String,
    onDismiss: () -> Unit,
    onVillageCreated: (City) -> Unit
) {
    val context = LocalContext.current
    var villageName by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("Maharashtra") }
    var isResolvingCoords by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = when (language) {
                        "mr" -> "आपले गाव प्रविष्ट करा 🏡"
                        "hi" -> "अपना गाँव प्रविष्ट करें 🏡"
                        else -> "Enter Your Village 🏡"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = villageName,
                    onValueChange = { villageName = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "गावाचे नाव (उदा. शिरवळ, केडगाव)"
                                "hi" -> "गाँव का नाम (उदा. शिरडी, केडगांव)"
                                else -> "Village Name (e.g. Shirwal)"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = district,
                    onValueChange = { district = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "तालुका किंवा जिल्हा (उदा. सातारा, पुणे)"
                                "hi" -> "तहसील या जिला (उदा. सतारा, पुणे)"
                                else -> "Taluka / District (e.g. Satara)"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "राज्य (उदा. महाराष्ट्र)"
                                "hi" -> "राज्य (उदा. महाराष्ट्र)"
                                else -> "State (e.g. Maharashtra)"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = when (language) {
                                "mr" -> "रद्द करा"
                                "hi" -> "रद्द करें"
                                else -> "Cancel"
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (villageName.isBlank()) {
                                Toast.makeText(context, "कृपया गावाचे नाव प्रविष्ट करा", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            isResolvingCoords = true
                            val query = "${villageName.trim()}, ${district.trim()}, ${state.trim()} India"
                            LocationHelper.searchVillages(context, query, language) { list ->
                                isResolvingCoords = false
                                if (list.isNotEmpty()) {
                                    val best = list.first()
                                    onVillageCreated(
                                        CityRepository.createVillage(
                                            name = villageName.trim(),
                                            hindiName = villageName.trim(),
                                            district = district.trim().ifBlank { best.district },
                                            state = state.trim().ifBlank { best.state },
                                            latitude = best.latitude,
                                            longitude = best.longitude
                                        )
                                    )
                                } else {
                                    // Default to Maharashtra coordinates if search unavailable
                                    val fallback = CityRepository.findNearestCity(19.0, 74.0)
                                    onVillageCreated(
                                        CityRepository.createVillage(
                                            name = villageName.trim(),
                                            hindiName = villageName.trim(),
                                            district = district.trim().ifBlank { null },
                                            state = state.trim().ifBlank { "Maharashtra" },
                                            latitude = fallback.latitude,
                                            longitude = fallback.longitude
                                        )
                                    )
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = KesariyaSaffron),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        if (isResolvingCoords) {
                            CircularProgressIndicator(modifier = Modifier.size(16.dp), color = Color.White, strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Text(
                            text = when (language) {
                                "mr" -> "गाव सेट करा 🪔"
                                "hi" -> "गाँव सेट करें 🪔"
                                else -> "Save Village 🪔"
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
