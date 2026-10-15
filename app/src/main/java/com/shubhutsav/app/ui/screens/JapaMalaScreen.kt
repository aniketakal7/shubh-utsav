package com.shubhutsav.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.JapaMalaRepository
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.SacredMantra
import com.shubhutsav.app.data.SacredSoundPlayer
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaMalaScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    val scope = rememberCoroutineScope()

    var selectedMantraId by remember { mutableStateOf(prefs.japaSelectedMantraId) }
    val currentMantra = remember(selectedMantraId) { JapaMalaRepository.getMantraById(selectedMantraId) }

    var currentBead by remember { mutableIntStateOf(0) }
    var malasCompletedSession by remember { mutableIntStateOf(0) }
    var totalMalasAllTime by remember { mutableIntStateOf(prefs.japaTotalMalas) }
    var totalChantsAllTime by remember { mutableIntStateOf(prefs.japaTotalChants) }

    var soundEnabled by remember { mutableStateOf(prefs.japaSoundEnabled) }
    var hapticsEnabled by remember { mutableStateOf(prefs.japaHapticsEnabled) }

    var showMantraSheet by remember { mutableStateOf(false) }
    var showCelebrationDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonScale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "button_scale"
    )

    fun handleBeadCount() {
        if (hapticsEnabled) SacredSoundPlayer.performHaptic(context)
        if (soundEnabled) SacredSoundPlayer.playBeadClick(context)

        val nextBead = currentBead + 1
        if (nextBead >= 108) {
            // Completed 1 full Mala!
            currentBead = 0
            malasCompletedSession += 1
            totalMalasAllTime += 1
            totalChantsAllTime += 108
            prefs.recordMalaCompleted(108)
            SacredSoundPlayer.playMalaComplete(context)
            showCelebrationDialog = true
        } else {
            currentBead = nextBead
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "डिजिटल जप माळ"
                    "hi" -> "डिजिटल जप माला"
                    else -> "Sacred Japa Mala"
                },
                language = language,
                onLanguageChange = onLanguageChange,
                isHindi = language != "en",
                onToggleLanguage = onToggleLanguage,
                onSettingsClick = { navController.navigate("settings") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Active Mantra Card
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = RoyalMaroon,
                    shadowElevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showMantraSheet = true }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(RoyalMaroon, RoyalMaroonDark)
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("📿", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = when (language) {
                                            "mr" -> currentMantra.nameMr
                                            "hi" -> currentMantra.nameHi
                                            else -> currentMantra.nameEn
                                        },
                                        style = MaterialTheme.typography.titleMedium,
                                        color = VedicGoldLight,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                TextButton(
                                    onClick = { showMantraSheet = true },
                                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> "बदला ▾"
                                            "hi" -> "बदलें ▾"
                                            else -> "Change ▾"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = currentMantra.mantraSanskrit,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = currentMantra.transliteration,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.White.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = when (language) {
                                        "mr" -> currentMantra.meaningMr
                                        "hi" -> currentMantra.meaningHi
                                        else -> currentMantra.meaningEn
                                    },
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Big Interactive Chanting Bead Button
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(240.dp)
                            .scale(buttonScale)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(KesariyaSaffron, RoyalMaroon)
                                )
                            )
                            .clickable(
                                interactionSource = interactionSource,
                                indication = ripple(bounded = true, radius = 120.dp, color = VedicGoldLight)
                            ) {
                                handleBeadCount()
                            }
                    ) {
                        // Outer decorative ring
                        Box(
                            modifier = Modifier
                                .size(216.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.25f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$currentBead",
                                    fontSize = 64.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = "/ 108",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = VedicGoldLight
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "स्पर्श करा (जप)"
                                        "hi" -> "स्पर्श करें (जप)"
                                        else -> "TAP TO CHANT"
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Progress bar for the 108 beads
                    LinearProgressIndicator(
                        progress = { (currentBead / 108f).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = KesariyaSaffron,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = when (language) {
                            "mr" -> "या सत्रात पूर्ण माळा: $malasCompletedSession"
                            "hi" -> "इस सत्र में पूर्ण माला: $malasCompletedSession"
                            else -> "Malas completed this session: $malasCompletedSession"
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Stats & Controls Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Total Malas Counter Card
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🏆 $totalMalasAllTime",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "एकूण माळा"
                                    "hi" -> "कुल मालाएँ"
                                    else -> "Total Malas"
                                },
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Total Chants Counter Card
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "✨ $totalChantsAllTime",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = KesariyaSaffron
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "एकूण जप संख्या"
                                    "hi" -> "कुल जाप संख्या"
                                    else -> "Total Chants"
                                },
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Sound, Haptics & Reset Toggles
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Sound Toggle
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {
                                soundEnabled = !soundEnabled
                                prefs.japaSoundEnabled = soundEnabled
                            }) {
                                Icon(
                                    imageVector = if (soundEnabled) Icons.Filled.VolumeUp else Icons.Filled.VolumeOff,
                                    contentDescription = "Sound toggle",
                                    tint = if (soundEnabled) KesariyaSaffron else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = if (soundEnabled) "Sound On" else "Silent",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Haptics Toggle
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {
                                hapticsEnabled = !hapticsEnabled
                                prefs.japaHapticsEnabled = hapticsEnabled
                            }) {
                                Icon(
                                    imageVector = if (hapticsEnabled) Icons.Filled.Vibration else Icons.Outlined.Smartphone,
                                    contentDescription = "Haptics toggle",
                                    tint = if (hapticsEnabled) KesariyaSaffron else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = if (hapticsEnabled) "Vibrate" else "No Vibrate",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Reset Button
                        TextButton(onClick = { showResetDialog = true }) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Reset",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "रीसेट"
                                    "hi" -> "रीसेट"
                                    else -> "Reset"
                                },
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Benefits Note
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🌸", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = currentMantra.benefits,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    // Mantra Selection Bottom Sheet
    if (showMantraSheet) {
        ModalBottomSheet(
            onDismissRequest = { showMantraSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = when (language) {
                        "mr" -> "जपासाठी पवित्र मंत्र निवडा"
                        "hi" -> "जाप हेतु पवित्र मंत्र चुनें"
                        else -> "Select Sacred Mantra for Japa"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(14.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxHeight(0.6f)
                ) {
                    items(JapaMalaRepository.mantras) { mantra ->
                        val isSelected = mantra.id == selectedMantraId
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = if (isSelected) BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary) else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedMantraId = mantra.id
                                    prefs.japaSelectedMantraId = mantra.id
                                    showMantraSheet = false
                                }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> mantra.nameMr
                                            "hi" -> mantra.nameHi
                                            else -> mantra.nameEn
                                        },
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    if (isSelected) {
                                        Icon(
                                            Icons.Filled.CheckCircle,
                                            contentDescription = "Selected",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = mantra.mantraSanskrit,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Mala Completion Celebration Dialog
    if (showCelebrationDialog) {
        AlertDialog(
            onDismissRequest = { showCelebrationDialog = false },
            title = {
                Text(
                    text = "🎉 " + when (language) {
                        "mr" -> "१ माळ (१०८ जप) पूर्ण!"
                        "hi" -> "1 माला (108 जाप) पूर्ण!"
                        else -> "1 Mala (108 Chants) Completed!"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = when (language) {
                            "mr" -> "अभिनंदन! तुम्ही १०८ पवित्र मणींचा जप पूर्ण केला आहे. देव तुमचे कल्याण करो!"
                            "hi" -> "साधुवाद! आपने 108 पावन मणियों का जप पूर्ण किया। प्रभु की असीम कृपा आप पर सदैव बनी रहे।"
                            else -> "Congratulations! You have completed 108 sacred chants. May your sadhana bring immense peace, health, and auspiciousness."
                        },
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "🏆 Total Malas: $totalMalasAllTime",
                        fontWeight = FontWeight.Bold,
                        color = KesariyaSaffron
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showCelebrationDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = KesariyaSaffron)
                ) {
                    Text(
                        text = when (language) {
                            "mr" -> "पुढे सुरू ठेवा"
                            "hi" -> "आगे जारी रखें"
                            else -> "Continue Japa"
                        }
                    )
                }
            }
        )
    }

    // Reset Confirmation Dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = when (language) {
                        "mr" -> "माळ रीसेट करायची?"
                        "hi" -> "माला रीसेट करें?"
                        else -> "Reset Current Count?"
                    }
                )
            },
            text = {
                Text(
                    text = when (language) {
                        "mr" -> "चालू मणींची संख्या ० वर रीसेट होईल. एकूण पूर्ण केलेल्या माळा सुरक्षित राहतील."
                        "hi" -> "वर्तमान मनकों की संख्या शून्य हो जाएगी। आपकी कुल पूर्ण मालाएँ सुरक्षित रहेंगी।"
                        else -> "This will reset current bead count back to 0. Your total completed malas will be preserved."
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    currentBead = 0
                    showResetDialog = false
                }) {
                    Text(
                        text = when (language) {
                            "mr" -> "रीसेट करा"
                            "hi" -> "रीसेट करें"
                            else -> "Reset"
                        },
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
