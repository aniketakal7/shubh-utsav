package com.shubhutsav.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.PanchangCalculator
import com.shubhutsav.app.data.ChoghadiyaSlot
import com.shubhutsav.app.data.ChoghadiyaType
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.AbhijitAuspicious
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RahuKaalWarning
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight
import java.util.Calendar

@Composable
fun ChoghadiyaScreen(
    navController: NavHostController,
    city: City,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Day, 1: Night
    val calendar = remember { Calendar.getInstance() }

    val allSlots = remember(city) { PanchangCalculator.calculateChoghadiya(city = city, calendar = calendar) }
    val daySlots = remember(allSlots) { allSlots.filter { it.isDay } }
    val nightSlots = remember(allSlots) { allSlots.filter { !it.isDay } }

    val currentSlot = remember(allSlots) { allSlots.find { it.isCurrent } }
    val dishaShool = remember { PanchangCalculator.getDishaShool(calendar) }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "दैनिक चौघडिया मुहूर्त"
                    "hi" -> "दैनिक चौघड़िया मुहूर्त"
                    else -> "Choghadiya Muhurat"
                },
                language = language,
                onLanguageChange = onLanguageChange,
                isHindi = language != "en",
                onToggleLanguage = onToggleLanguage,
                cityName = city.displayName(language),
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // "Active Right Now" Choghadiya Hero Card
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
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
                            .padding(20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(if (currentSlot?.type?.isAuspicious == true) Color(0xFF4CAF50) else Color(0xFFFF5252))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = when (language) {
                                            "mr" -> "आत्ता चालू असलेला मुहूर्त"
                                            "hi" -> "वर्तमान सक्रिय चौघड़िया"
                                            else -> "ACTIVE RIGHT NOW"
                                        },
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp,
                                        color = VedicGoldLight
                                    )
                                }
                                Text(
                                    text = if (currentSlot?.isDay == true) "☀️ Day" else "🌙 Night",
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            if (currentSlot != null) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = when (language) {
                                                "mr" -> currentSlot.type.nameMr
                                                "hi" -> currentSlot.type.nameHi
                                                else -> currentSlot.type.nameEn
                                            },
                                            fontSize = 26.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                        Text(
                                            text = when (language) {
                                                "mr" -> currentSlot.type.qualityMr
                                                "hi" -> currentSlot.type.qualityHi
                                                else -> currentSlot.type.qualityEn
                                            },
                                            fontSize = 13.sp,
                                            color = if (currentSlot.type.isAuspicious) Color(0xFF81C784) else Color(0xFFFF8A80),
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color.White.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = "${currentSlot.startTime} – ${currentSlot.endTime}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "Transitioning Muhurat...",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Disha Shool & Remedy Card (दिशा शूल)
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🧭", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "आजचा दिशा शूल (प्रवास नियम)"
                                        "hi" -> "आज का दिशा शूल (यात्रा नियम)"
                                        else -> "Today's Disha Shool"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = RahuKaalWarning.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = when (language) {
                                        "mr" -> dishaShool.directionMr
                                        "hi" -> dishaShool.directionHi
                                        else -> dishaShool.directionEn
                                    },
                                    color = RahuKaalWarning,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = when (language) {
                                "mr" -> "निवारण उपाय: " + dishaShool.remedyMr
                                "hi" -> "यात्रा निवारण उपाय: " + dishaShool.remedyHi
                                else -> "Vedic Remedy: " + dishaShool.remedyEn
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Day / Night Tab Bar
            item {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = KesariyaSaffron,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Text(
                                text = when (language) {
                                    "mr" -> "☀️ दिवस चौघडिया (८ भाग)"
                                    "hi" -> "☀️ दिन का चौघड़िया (8 भाग)"
                                    else -> "☀️ Day Choghadiya"
                                },
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Text(
                                text = when (language) {
                                    "mr" -> "🌙 रात्र चौघडिया (८ भाग)"
                                    "hi" -> "🌙 रात्रि चौघड़िया (8 भाग)"
                                    else -> "🌙 Night Choghadiya"
                                },
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                }
            }

            // Choghadiya Slots List
            val displaySlots = if (selectedTab == 0) daySlots else nightSlots
            items(displaySlots) { slot ->
                ChoghadiyaSlotCard(
                    slot = slot,
                    language = language
                )
            }
        }
    }
}

@Composable
private fun ChoghadiyaSlotCard(
    slot: ChoghadiyaSlot,
    language: String
) {
    val isAuspicious = slot.type.isAuspicious
    val badgeColor = when (slot.type) {
        ChoghadiyaType.AMRIT -> Color(0xFF2E7D32) // Forest green
        ChoghadiyaType.SHUBH -> Color(0xFF388E3C)
        ChoghadiyaType.LABH -> Color(0xFF00796B) // Teal green
        ChoghadiyaType.CHAR -> Color(0xFFF57C00) // Amber orange
        ChoghadiyaType.ROG -> Color(0xFFD32F2F) // Red
        ChoghadiyaType.KAAL -> Color(0xFFC2185B) // Dark Pink / Magenta
        ChoghadiyaType.UDVEG -> Color(0xFFE64A19) // Deep Orange
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (slot.isCurrent) badgeColor.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
        border = if (slot.isCurrent) BorderStroke(2.dp, badgeColor) else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Colored status indicator dot / icon
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(badgeColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (slot.type) {
                            ChoghadiyaType.AMRIT -> "✨"
                            ChoghadiyaType.SHUBH -> "🪔"
                            ChoghadiyaType.LABH -> "💰"
                            ChoghadiyaType.CHAR -> "🚗"
                            ChoghadiyaType.ROG -> "⚠️"
                            ChoghadiyaType.KAAL -> "⛔"
                            ChoghadiyaType.UDVEG -> "⏳"
                        },
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = when (language) {
                                "mr" -> slot.type.nameMr
                                "hi" -> slot.type.nameHi
                                else -> slot.type.nameEn
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (slot.isCurrent) badgeColor else MaterialTheme.colorScheme.onSurface
                        )
                        if (slot.isCurrent) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = badgeColor
                            ) {
                                Text(
                                    text = "LIVE",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = when (language) {
                            "mr" -> slot.type.qualityMr
                            "hi" -> slot.type.qualityHi
                            else -> slot.type.qualityEn
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isAuspicious) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Time slot badge
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
            ) {
                Text(
                    text = "${slot.startTime} – ${slot.endTime}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}
