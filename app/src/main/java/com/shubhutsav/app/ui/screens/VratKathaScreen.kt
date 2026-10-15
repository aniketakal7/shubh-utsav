package com.shubhutsav.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.SacredVrat
import com.shubhutsav.app.data.VratRepository
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VratKathaScreen(
    navController: NavHostController,
    language: String,
    initialVratId: String? = null,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    var selectedVratId by remember { mutableStateOf(initialVratId ?: "ekadashi") }
    val currentVrat = remember(selectedVratId) { VratRepository.getVratById(selectedVratId) }

    var selectedTab by remember { mutableIntStateOf(0) } // 0: Katha, 1: Fasting Rules & Parana, 2: Puja Vidhi
    var fontSizeMultiplier by remember { mutableFloatStateOf(1f) }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "व्रत कथा व पारण"
                    "hi" -> "व्रत कथा एवं पारण"
                    else -> "Vrat Katha & Parana"
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Horizontal Carousel of Vrats
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(VratRepository.vrats) { vrat ->
                        val isSelected = vrat.id == selectedVratId
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedVratId = vrat.id },
                            label = {
                                Text(
                                    text = when (language) {
                                        "mr" -> vrat.nameMr.substringBefore(" (")
                                        "hi" -> vrat.nameHi.substringBefore(" (")
                                        else -> vrat.nameEn.substringBefore(" (")
                                    },
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = KesariyaSaffron,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Hero Header Card for Selected Vrat
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
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color.White.copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        text = currentVrat.deity,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = VedicGoldLight,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                                Text(
                                    text = currentVrat.frequency,
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = when (language) {
                                    "mr" -> currentVrat.nameMr
                                    "hi" -> currentVrat.nameHi
                                    else -> currentVrat.nameEn
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = when (language) {
                                    "mr" -> currentVrat.significanceMr
                                    "hi" -> currentVrat.significanceHi
                                    else -> currentVrat.significanceEn
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
            }

            // Tab Selector: 0: Katha, 1: Fasting & Parana, 2: Puja Vidhi
            item {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = KesariyaSaffron,
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Text(
                                text = when (language) {
                                    "mr" -> "📖 कथा"
                                    "hi" -> "📖 व्रत कथा"
                                    else -> "📖 Katha"
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
                                    "mr" -> "🍲 पारण व फलाहार"
                                    "hi" -> "🍲 पारण व फलाहार"
                                    else -> "🍲 Parana & Food"
                                },
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                    Tab(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        text = {
                            Text(
                                text = when (language) {
                                    "mr" -> "🪔 विधी"
                                    "hi" -> "🪔 विधि"
                                    else -> "🪔 Vidhi"
                                },
                                fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                }
            }

            // TAB 0: Vrat Katha (Story) with Font Size Controls
            if (selectedTab == 0) {
                item {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            // Header with Font Size Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = currentVrat.kathaTitle,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { fontSizeMultiplier = (fontSizeMultiplier - 0.15f).coerceAtLeast(0.85f) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Text("A-", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    IconButton(
                                        onClick = { fontSizeMultiplier = (fontSizeMultiplier + 0.15f).coerceAtMost(1.6f) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Text("A+", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            val storyText = when (language) {
                                "mr" -> currentVrat.kathaStoryMr
                                "hi" -> currentVrat.kathaStoryHi
                                else -> currentVrat.kathaStoryEn
                            }

                            Text(
                                text = storyText,
                                fontSize = (15 * fontSizeMultiplier).sp,
                                lineHeight = (24 * fontSizeMultiplier).sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }

            // TAB 1: Parana Timing & Fasting Food Rules
            if (selectedTab == 1) {
                // Parana Timing Guide Card
                item {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFF8E1), // Warm cream gold
                        border = BorderStroke(1.dp, VedicGold),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🌅", fontSize = 22.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "पारण नियम व वेळ"
                                        "hi" -> "पारण का समय व नियम"
                                        else -> "Parana Guidelines & Timings"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalMaroon
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> currentVrat.paranaGuideMr
                                    "hi" -> currentVrat.paranaGuideHi
                                    else -> currentVrat.paranaGuideEn
                                },
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                color = Color(0xFF3E2723)
                            )
                        }
                    }
                }

                // Allowed Foods (फलाहार)
                item {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("✅", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "उपवासात काय चालते (फलाहार)"
                                        "hi" -> "उपवास में क्या खाएं (फलाहार)"
                                        else -> "Allowed Fasting Foods"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E7D32)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            currentVrat.allowedFoods.forEach { food ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("•", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(food, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    }
                }

                // Strictly Prohibited Foods (वर्जित भोजन)
                item {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("⛔", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "उपवासात काय वर्ज्य आहे"
                                        "hi" -> "उपवास में क्या न खाएं (वर्जित)"
                                        else -> "Prohibited Foods"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            currentVrat.prohibitedFoods.forEach { food ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("✕", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(food, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    }
                }
            }

            // TAB 2: Puja Vidhi Steps
            if (selectedTab == 2) {
                item {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = when (language) {
                                    "mr" -> "पूजा विधी व नियम"
                                    "hi" -> "पूजा विधि एवं नियम"
                                    else -> "Puja Vidhi Steps"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(14.dp))

                            currentVrat.pujaVidhiSteps.forEachIndexed { index, step ->
                                Row(
                                    modifier = Modifier.padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(KesariyaSaffron.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "${index + 1}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalMaroon
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = step,
                                        fontSize = 14.sp,
                                        lineHeight = 22.sp,
                                        color = MaterialTheme.colorScheme.onSurface
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
