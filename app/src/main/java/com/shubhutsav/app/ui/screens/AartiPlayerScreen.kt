package com.shubhutsav.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.AartiRepository
import com.shubhutsav.app.data.SacredAarti
import com.shubhutsav.app.data.SacredSoundPlayer
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AartiPlayerScreen(
    navController: NavHostController,
    language: String,
    initialAartiId: String? = null,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var selectedAartiId by remember { mutableStateOf(initialAartiId ?: "ganesh_aarti") }
    val currentAarti = remember(selectedAartiId) { AartiRepository.getAartiById(selectedAartiId) }

    var isPlaying by remember { mutableStateOf(false) }
    var currentLineIndex by remember { mutableIntStateOf(0) }
    var isTanpuraActive by remember { mutableStateOf(false) }
    var showAartiPicker by remember { mutableStateOf(false) }

    // Bell swing animation
    val bellRotation = remember { Animatable(0f) }
    // Shankh pulse animation
    val shankhScale = remember { Animatable(1f) }

    // Automatic lyric synchronization timer
    LaunchedEffect(isPlaying, currentAarti) {
        if (isPlaying) {
            val totalLines = currentAarti.lines.size
            val lineDurationMs = (currentAarti.durationApproxSeconds * 1000L) / totalLines
            while (isPlaying) {
                delay(lineDurationMs)
                if (currentLineIndex < totalLines - 1) {
                    currentLineIndex += 1
                    listState.animateScrollToItem((currentLineIndex - 1).coerceAtLeast(0))
                } else {
                    currentLineIndex = 0
                    listState.animateScrollToItem(0)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            SacredSoundPlayer.stopTanpura()
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "आरती व चालीसा"
                    "hi" -> "आरती व चालीसा"
                    else -> "Aarti & Chalisa Player"
                },
                language = language,
                onLanguageChange = onLanguageChange,
                isHindi = language != "en",
                onToggleLanguage = onToggleLanguage,
                onSettingsClick = { navController.navigate("settings") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Interactive Virtual Puja Instruments Header (Bell & Conch)
            Surface(
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
                color = RoyalMaroon,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(listOf(RoyalMaroon, RoyalMaroonDark))
                        )
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Interactive Temple Bell
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    scope.launch {
                                        bellRotation.animateTo(25f, tween(100))
                                        bellRotation.animateTo(-25f, tween(120))
                                        bellRotation.animateTo(12f, tween(100))
                                        bellRotation.animateTo(0f, tween(120))
                                    }
                                    SacredSoundPlayer.playTempleBell(context)
                                }
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(VedicGold.copy(alpha = 0.25f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🔔",
                                    fontSize = 26.sp,
                                    modifier = Modifier.rotate(bellRotation.value)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "घंटी वाजवा"
                                    "hi" -> "घंटी बजाएं"
                                    else -> "Ring Bell"
                                },
                                fontSize = 11.sp,
                                color = VedicGoldLight,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Center Virtual Diya
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("🪔", fontSize = 34.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "पवित्र आरती कक्ष"
                                    "hi" -> "पावन आरती कक्ष"
                                    else -> "Temple Room"
                                },
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Interactive Sacred Conch (Shankh)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    scope.launch {
                                        shankhScale.animateTo(1.25f, tween(150))
                                        shankhScale.animateTo(1f, tween(250))
                                    }
                                    SacredSoundPlayer.playShankhNaad(context)
                                }
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(KesariyaSaffron.copy(alpha = 0.25f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🐚",
                                    fontSize = 26.sp,
                                    modifier = Modifier.scale(shankhScale.value)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "शंखनाद"
                                    "hi" -> "शंखनाद"
                                    else -> "Blow Conch"
                                },
                                fontSize = 11.sp,
                                color = VedicGoldLight,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Current Aarti Title Bar with Change Button
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAartiPicker = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = when (language) {
                                "mr" -> currentAarti.titleMr
                                "hi" -> currentAarti.titleHi
                                else -> currentAarti.titleEn
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${currentAarti.deity} • ${currentAarti.category}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    FilledTonalButton(
                        onClick = { showAartiPicker = true },
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = when (language) {
                                "mr" -> "यादी ▾"
                                "hi" -> "सूची ▾"
                                else -> "Select ▾"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Synchronized Scrolling Lyrics Display
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(currentAarti.lines) { index, line ->
                    val isCurrent = index == currentLineIndex && isPlaying
                    val cardBg = if (isCurrent) KesariyaSaffron.copy(alpha = 0.15f) else Color.Transparent
                    val borderColor = if (isCurrent) KesariyaSaffron else Color.Transparent

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = cardBg,
                        border = BorderStroke(1.5.dp, borderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                currentLineIndex = index
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = when (language) {
                                    "mr" -> line.marathi
                                    "hi" -> line.hindi
                                    else -> line.hindi
                                },
                                fontSize = if (isCurrent) 19.sp else 16.sp,
                                fontWeight = if (isCurrent) FontWeight.ExtraBold else FontWeight.Normal,
                                color = if (isCurrent) RoyalMaroon else MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                lineHeight = 26.sp
                            )
                            if (language == "en") {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = line.english,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Audio Player Controls Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 16.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    // Progress indicator
                    LinearProgressIndicator(
                        progress = {
                            if (currentAarti.lines.isNotEmpty()) {
                                (currentLineIndex.toFloat() / (currentAarti.lines.size - 1)).coerceIn(0f, 1f)
                            } else 0f
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = KesariyaSaffron,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ambient Tanpura Toggle
                        IconButton(
                            onClick = {
                                SacredSoundPlayer.toggleTanpura { isActive ->
                                    isTanpuraActive = isActive
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isTanpuraActive) Icons.Filled.GraphicEq else Icons.Outlined.GraphicEq,
                                contentDescription = "Tanpura Drone",
                                tint = if (isTanpuraActive) KesariyaSaffron else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Previous Line
                        IconButton(
                            onClick = {
                                currentLineIndex = (currentLineIndex - 1).coerceAtLeast(0)
                            }
                        ) {
                            Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous")
                        }

                        // Play / Pause Floating Button
                        FilledIconButton(
                            onClick = { isPlaying = !isPlaying },
                            colors = IconButtonDefaults.filledIconButtonColors(containerColor = KesariyaSaffron),
                            modifier = Modifier.size(54.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        // Next Line
                        IconButton(
                            onClick = {
                                currentLineIndex = (currentLineIndex + 1).coerceAtMost(currentAarti.lines.size - 1)
                            }
                        ) {
                            Icon(Icons.Filled.SkipNext, contentDescription = "Next")
                        }

                        // Reset to Start
                        IconButton(
                            onClick = {
                                currentLineIndex = 0
                                isPlaying = false
                            }
                        ) {
                            Icon(Icons.Filled.Replay, contentDescription = "Replay")
                        }
                    }
                }
            }
        }
    }

    // Aarti Selector Modal Bottom Sheet
    if (showAartiPicker) {
        ModalBottomSheet(
            onDismissRequest = { showAartiPicker = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = when (language) {
                        "mr" -> "आरती व चालीसा संग्रह"
                        "hi" -> "आरती एवं चालीसा संग्रह"
                        else -> "Select Aarti or Chalisa"
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
                    itemsIndexed(AartiRepository.aartis) { _, aarti ->
                        val isSelected = aarti.id == selectedAartiId
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = if (isSelected) BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary) else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedAartiId = aarti.id
                                    currentLineIndex = 0
                                    isPlaying = false
                                    showAartiPicker = false
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> aarti.titleMr
                                            "hi" -> aarti.titleHi
                                            else -> aarti.titleEn
                                        },
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "${aarti.deity} • ${aarti.category}",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                if (isSelected) {
                                    Icon(
                                        Icons.Filled.CheckCircle,
                                        contentDescription = "Selected",
                                        tint = MaterialTheme.colorScheme.primary
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
