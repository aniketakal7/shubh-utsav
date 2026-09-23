package com.shubhutsav.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.CityRepository
import com.shubhutsav.app.location.LocationHelper
import com.shubhutsav.app.location.LocationResult
import com.shubhutsav.app.ui.components.CityPickerDialog
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.OnboardingHeroGradient
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.VedicGold

@Composable
fun OnboardingScreen(
    onComplete: (isHindi: Boolean, cityId: String, ritualStyle: String) -> Unit = { _, _, _ -> },
    onCompleteLanguage: (language: String, cityId: String, ritualStyle: String) -> Unit = { lang, city, ritual ->
        onComplete(lang == "hi", city, ritual)
    }
) {
    val context = LocalContext.current
    var selectedLanguage by remember { mutableStateOf("en") }
    val isHindi = selectedLanguage == "hi"
    var selectedCity by remember { mutableStateOf(CityRepository.getCityById("delhi")) }
    var showCityPicker by remember { mutableStateOf(false) }
    var selectedRitualStyle by remember { mutableStateOf("General") }
    var isDetectingLocation by remember { mutableStateOf(false) }
    var showGpsPromptDialog by remember { mutableStateOf(false) }

    fun processLocationDetection() {
        isDetectingLocation = true
        LocationHelper.detectNearestCity(context) { result ->
            isDetectingLocation = false
            when (result) {
                is LocationResult.Success -> {
                    val detected = result.detectedLocation
                    val toastMsg = when (selectedLanguage) {
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
                    selectedCity = detected.city
                }
                is LocationResult.PermissionRequired -> {
                    val msg = when (selectedLanguage) {
                        "mr" -> "स्थान परवानगी आवश्यक आहे."
                        "hi" -> "स्थान अनुमति आवश्यक है।"
                        else -> "Location permission is required."
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
                is LocationResult.LocationDisabled -> {
                    showGpsPromptDialog = true
                }
                is LocationResult.Error -> {
                    val msg = when (selectedLanguage) {
                        "mr" -> "स्थान शोधता आले नाही. कृपया सूचीमधून शहर निवडा."
                        "hi" -> "स्थान खोजने में असमर्थ। कृपया सूची से चुनें।"
                        else -> "Unable to detect GPS location. Please select manually."
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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
            val deniedMsg = when (selectedLanguage) {
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

    val topCities = listOf("delhi", "mumbai", "pune", "nashik", "bengaluru", "kolkata", "chennai", "hyderabad", "jaipur", "varanasi")

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
                    text = when (selectedLanguage) {
                        "mr" -> "स्थान सेवा (GPS) बंद आहे"
                        "hi" -> "लोकेशन सेवा (GPS) बंद है"
                        else -> "Location Services Disabled"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = when (selectedLanguage) {
                        "mr" -> "आपोआप अचूक पंचांग आणि मुहूर्ताचे शहर निवडण्यासाठी कृपया आपल्या फोनचे GPS चालू करा."
                        "hi" -> "सटीक पंचांग और मुहूर्त का शहर स्वतः चुनने के लिए कृपया अपने फोन का GPS चालू करें।"
                        else -> "Please turn on GPS/Location in your device settings to automatically detect your closest city."
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
                        text = when (selectedLanguage) {
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
                        text = when (selectedLanguage) {
                            "mr" -> "रद्द करा"
                            "hi" -> "रद्द करें"
                            else -> "Cancel"
                        }
                    )
                }
            }
        )
    }

    if (showCityPicker) {
        CityPickerDialog(
            currentCityId = selectedCity.id,
            isHindi = isHindi,
            language = selectedLanguage,
            onCitySelected = { selectedCity = it },
            onDismiss = { showCityPicker = false }
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            // Welcoming Spiritual Medallion
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(OnboardingHeroGradient),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🪔", fontSize = 44.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = when (selectedLanguage) {
                    "mr" -> "शुभ उत्सव मध्ये आपले स्वागत आहे"
                    "hi" -> "शुभ उत्सव में आपका स्वागत है"
                    else -> "Welcome to Shubh Utsav"
                },
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = when (selectedLanguage) {
                    "mr" -> "सण, दैनिक पंचांग आणि गृह-पूजेचा आपला सोपा, आधुनिक आणि विश्वासू सोबती."
                    "hi" -> "त्योहारों, दैनिक पंचांग और गृह-पूजा का आपका सरल, आधुनिक एवं विश्वसनीय साथी।"
                    else -> "Your peaceful, modern companion to celebrate festivals and perform pujas at home with confidence."
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Step 1: Language Selection (English, Hindi, Marathi)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = KesariyaSaffron,
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "1", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        "mr" -> "पसंतीची भाषा निवडा"
                        "hi" -> "पसंदीदा भाषा चुनें"
                        else -> "Choose Your Preferred Language"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    LanguageSelectionCard(
                        title = "English",
                        subtitle = "Simple English guidance",
                        isSelected = selectedLanguage == "en",
                        onClick = { selectedLanguage = "en" },
                        modifier = Modifier.weight(1f)
                    )

                    LanguageSelectionCard(
                        title = "हिंदी",
                        subtitle = "सरल हिंदी में संपूर्ण विधि",
                        isSelected = selectedLanguage == "hi",
                        onClick = { selectedLanguage = "hi" },
                        modifier = Modifier.weight(1f)
                    )
                }

                LanguageSelectionCard(
                    title = "मराठी",
                    subtitle = "सोप्या मराठीत संपूर्ण पूजा विधी, मंत्र व माहिती",
                    isSelected = selectedLanguage == "mr",
                    onClick = { selectedLanguage = "mr" },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step 2: City / Location for Accurate Panchang
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = KesariyaSaffron,
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "2", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        "mr" -> "आपले शहर निवडा (अचूक पंचांगासाठी)"
                        "hi" -> "अपना शहर चुनें (सटीक पंचांग हेतु)"
                        else -> "Select City (For accurate timings)"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Current selected city box
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .clickable { showCityPicker = true }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(KesariyaSaffron.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = KesariyaSaffron,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (selectedLanguage == "en") selectedCity.name else selectedCity.hindiName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (selectedLanguage == "en") selectedCity.state else selectedCity.hindiState,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = when (selectedLanguage) {
                            "mr" -> "बदला >"
                            "hi" -> "बदलें >"
                            else -> "Change >"
                        },
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick City Chips + Auto Detect Action
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    AssistChip(
                        onClick = { requestAutoDetect() },
                        label = {
                            Text(
                                text = when (selectedLanguage) {
                                    "mr" -> if (isDetectingLocation) "शोधत आहे..." else "GPS ने ओळखा"
                                    "hi" -> if (isDetectingLocation) "खोज रहे हैं..." else "GPS से पहचानें"
                                    else -> if (isDetectingLocation) "Detecting..." else "Auto-Detect (GPS)"
                                },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KesariyaSaffron
                            )
                        },
                        leadingIcon = {
                            if (isDetectingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(13.dp),
                                    strokeWidth = 2.dp,
                                    color = KesariyaSaffron
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.MyLocation,
                                    contentDescription = "Auto Detect Location",
                                    modifier = Modifier.size(15.dp),
                                    tint = KesariyaSaffron
                                )
                            }
                        },
                        border = BorderStroke(1.dp, KesariyaSaffron.copy(alpha = 0.6f)),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = KesariyaSaffron.copy(alpha = 0.08f)
                        )
                    )
                }

                items(topCities) { cityId ->
                    val city = CityRepository.getCityById(cityId)
                    val isSelected = selectedCity.id == cityId
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCity = city },
                        label = { Text(if (selectedLanguage == "en") city.name else city.hindiName, fontSize = 12.sp) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step 3: Ritual Tradition
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = KesariyaSaffron,
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "3", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        "mr" -> "पूजा परंपरा (ऐच्छिक)"
                        "hi" -> "पूजा परंपरा (वैकल्पिक)"
                        else -> "Ritual Tradition (Optional)"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "General" to when (selectedLanguage) { "mr" -> "सामान्य"; "hi" -> "सामान्य"; else -> "General" },
                    "North Indian" to when (selectedLanguage) { "mr" -> "उत्तर भारतीय"; "hi" -> "उत्तर भारतीय"; else -> "North Indian" },
                    "South Indian" to when (selectedLanguage) { "mr" -> "दक्षिण भारतीय"; "hi" -> "दक्षिण भारतीय"; else -> "South Indian" }
                ).forEach { (styleKey, label) ->
                    val isSelected = selectedRitualStyle == styleKey
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedRitualStyle = styleKey },
                        label = { Text(label, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            Button(
                onClick = {
                    onComplete(selectedLanguage == "hi", selectedCity.id, selectedRitualStyle)
                    onCompleteLanguage(selectedLanguage, selectedCity.id, selectedRitualStyle)
                },
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RoyalMaroon),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = when (selectedLanguage) {
                        "mr" -> "प्रवास सुरू करा 🪔"
                        "hi" -> "यात्रा प्रारंभ करें 🪔"
                        else -> "Begin Your Journey 🪔"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun LanguageSelectionCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
            else
                MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                if (isSelected) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
