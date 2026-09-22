package com.shubhutsav.app.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.*
import com.shubhutsav.app.notifications.NotificationHelper
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.components.ExplainSimplyDialog
import com.shubhutsav.app.ui.theme.AuspiciousRed
import com.shubhutsav.app.ui.theme.CardBorderGold
import com.shubhutsav.app.ui.theme.CardBorderSubtle
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.PanchangCardGradient
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.SuccessGreen
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight

@Composable
fun FestivalDetailScreen(
    navController: NavHostController,
    festival: Festival,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onLanguageChange: (String) -> Unit = {},
    onToggleLanguage: () -> Unit = {}
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val prefs = remember { PreferencesManager(context) }

    var selectedTab by remember { mutableStateOf(0) }
    var showExplainSimply by remember { mutableStateOf(false) }

    // Persistent step tracking state
    var completedStepsCount by remember {
        mutableStateOf(prefs.getCompletedStepsCount(festival.id, festival.steps.size))
    }
    var stepToggleCounter by remember { mutableStateOf(0) }

    // Reminders toggles
    var prepReminder by remember { mutableStateOf(prefs.isReminderActive(festival.id, "prep")) }
    var muhuratReminder by remember { mutableStateOf(prefs.isReminderActive(festival.id, "muhurat")) }
    var shoppingReminder by remember { mutableStateOf(prefs.isReminderActive(festival.id, "shopping")) }

    val tabs = listOf(
        when (language) { "mr" -> "महत्त्व व मंत्र"; "hi" -> "महत्व व मंत्र"; else -> "Significance" },
        when (language) { "mr" -> "पूजा विधी"; "hi" -> "पूजा विधि"; else -> "Puja Vidhi" },
        when (language) { "mr" -> "तयारी टाइमलाइन"; "hi" -> "तैयारी टाइमलाइन"; else -> "Preparation" },
        when (language) { "mr" -> "रिमाइंडर"; "hi" -> "रिमाइंडर"; else -> "Reminders" }
    )

    if (showExplainSimply) {
        ExplainSimplyDialog(
            festival = festival,
            isHindi = language == "hi",
            language = language,
            onDismiss = { showExplainSimply = false }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = festival.getName(language),
                isHindi = language == "hi",
                language = language,
                onLanguageChange = onLanguageChange,
                onToggleLanguage = onToggleLanguage,
                onBackClick = { navController.popBackStack() },
                onSettingsClick = { navController.navigate("settings") }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Button(
                    onClick = { navController.navigate("shopping/${festival.id}") },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .height(52.dp)
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = when (language) {
                            "mr" -> "पूजा साहित्य चेकलिस्ट (${festival.supplies.size} वस्तू)"
                            "hi" -> "पूजा सामग्री चेकलिस्ट (${festival.supplies.size} वस्तुएं)"
                            else -> "Open Shopping Checklist (${festival.supplies.size} items)"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Banner: Emoji medallion, Date, Category & Summary
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = festival.emoji, fontSize = 34.sp)
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = festival.getName(language),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "📅 ${festival.getDate2026(language)}",
                                        color = KesariyaSaffron,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = KesariyaSaffron.copy(alpha = 0.12f)
                                    ) {
                                        Text(
                                            text = festival.getCategory(language),
                                            color = KesariyaSaffron,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = festival.getSummary(language),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Auspicious Muhurat Highlight Showcase Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = RoyalMaroon),
                    border = BorderStroke(1.dp, Color(0xFF8A3043).copy(alpha = 0.5f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .background(PanchangCardGradient)
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "✨", fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = festival.muhurat.getName(language),
                                    color = VedicGold,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.White.copy(alpha = 0.12f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = null,
                                        tint = VedicGoldLight,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = when (language) {
                                            "mr" -> "शुभ काळ"
                                            "hi" -> "शुभ काल"
                                            else -> "Auspicious"
                                        },
                                        color = VedicGoldLight,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = festival.muhurat.getTime(language),
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = festival.muhurat.getNote(language),
                            color = VedicGoldLight,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Custom Capsule / Pill Tabs Row
            item {
                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    edgePadding = 0.dp,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary,
                    divider = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    tabs.forEachIndexed { index, tabTitle ->
                        val isSelected = selectedTab == index
                        Tab(
                            selected = isSelected,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    text = tabTitle,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 13.sp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        )
                    }
                }
            }

            // TAB 0: Overview, Significance & Sacred Mantras
            if (selectedTab == 0) {
                item {
                    // "Explain Simply" Hero Action Card
                    Button(
                        onClick = { showExplainSimply = true },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = KesariyaSaffron
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "सोप्या भाषेत समजून घ्या (नवीन साधकांसाठी)"
                                "hi" -> "सरल शब्दों में समझें (नए साधकों हेतु)"
                                else -> "Explain Simply (For Beginners & Kids)"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "📖", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "उत्सवाचे महत्त्व व पावन कथा"
                                        "hi" -> "उत्सव का महत्व व पावन कथा"
                                        else -> "Significance & Divine Story"
                                    },
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = festival.getSignificance(language),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🕉️", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "पावन मंत्र आणि भावार्थ"
                                "hi" -> "पावन मंत्र एवं भावार्थ"
                                else -> "Sacred Mantras & Meanings"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                items(festival.mantras) { mantra ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = mantra.mantraSanskrit,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    lineHeight = 26.sp,
                                    modifier = Modifier.weight(1f)
                                )

                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(mantra.mantraSanskrit))
                                        Toast.makeText(
                                             context,
                                             when (language) {
                                                 "mr" -> "मंत्र कॉपी केला!"
                                                 "hi" -> "मंत्र कॉपी किया गया!"
                                                 else -> "Mantra copied to clipboard!"
                                             },
                                             Toast.LENGTH_SHORT
                                         ).show()
                                     },
                                     modifier = Modifier.size(32.dp)
                                 ) {
                                     Icon(
                                         imageVector = Icons.Default.ContentCopy,
                                         contentDescription = "Copy Mantra",
                                         tint = KesariyaSaffron,
                                         modifier = Modifier.size(16.dp)
                                     )
                                 }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = mantra.transliteration,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 10.dp),
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                            )

                            Text(
                                text = when (language) {
                                    "mr" -> "भावार्थ:"
                                    "hi" -> "भावार्थ:"
                                    else -> "Meaning:"
                                },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KesariyaSaffron
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = mantra.getMeaning(language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // TAB 1: Interactive Step-by-Step Puja Vidhi
            if (selectedTab == 1) {
                item {
                    val progressFraction = if (festival.steps.isEmpty()) 0f else completedStepsCount.toFloat() / festival.steps.size
                    val isAllCompleted = completedStepsCount == festival.steps.size && festival.steps.isNotEmpty()

                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isAllCompleted) SuccessGreen.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (isAllCompleted) SuccessGreen.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (isAllCompleted) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = SuccessGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                    Text(
                                        text = when (language) {
                                            "mr" -> "पूजा प्रगती: $completedStepsCount/${festival.steps.size} पायऱ्या पूर्ण"
                                            "hi" -> "पूजा प्रगति: $completedStepsCount/${festival.steps.size} चरण पूर्ण"
                                            else -> "Puja Progress: $completedStepsCount/${festival.steps.size} steps done"
                                        },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = if (isAllCompleted) SuccessGreen else MaterialTheme.colorScheme.primary
                                    )
                                }

                                TextButton(
                                    onClick = {
                                        prefs.resetFestivalProgress(festival.id, festival.steps.size, festival.supplies.size)
                                        completedStepsCount = 0
                                        stepToggleCounter++
                                        Toast.makeText(
                                            context,
                                            when (language) {
                                                "mr" -> "चेकलिस्ट रीसेट केली"
                                                "hi" -> "चेकलिस्ट रीसेट की गई"
                                                else -> "Checklist reset"
                                            },
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(15.dp))
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

                            Spacer(modifier = Modifier.height(10.dp))

                            LinearProgressIndicator(
                                progress = { progressFraction },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = if (isAllCompleted) SuccessGreen else KesariyaSaffron
                            )

                            if (isAllCompleted) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "🎉 अभिनंदन! आपण सर्व पूजा पायऱ्या पूर्ण केल्या आहेत. आपली पूजा शुभ आणि फलदायी होवो."
                                        "hi" -> "🎉 बधाई! आपने सभी पूजा चरण पूर्ण कर लिए हैं। आपका अनुष्ठान शुभ और फलदायी हो।"
                                        else -> "🎉 Wonderful! You have completed all puja steps. May your prayers bring peace and blessings."
                                    },
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    color = SuccessGreen,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }

                // Interactive Steps List
                itemsIndexed(festival.steps) { index, step ->
                    val isChecked = remember(index, stepToggleCounter) {
                        prefs.isStepCompleted(festival.id, index)
                    }

                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isChecked)
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (isChecked) SuccessGreen.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                prefs.toggleStep(festival.id, index)
                                completedStepsCount = prefs.getCompletedStepsCount(festival.id, festival.steps.size)
                                stepToggleCounter++
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            // Circular Step Number / Checkmark Badge
                            Surface(
                                shape = CircleShape,
                                color = if (isChecked) SuccessGreen else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(top = 2.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    if (isChecked) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Text(
                                            text = "${step.stepNumber}",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = step.getTitle(language),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = step.getInstruction(language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                if (step.mantra != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "ॐ ${step.mantra}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        )
                                    }
                                }

                                val practicalTip = step.getPracticalTip(language)
                                if (practicalTip != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = KesariyaSaffron.copy(alpha = 0.1f)
                                    ) {
                                        Text(
                                            text = "💡 $practicalTip",
                                            fontSize = 11.sp,
                                            color = KesariyaSaffron,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // TAB 2: Connected Vertical Preparation Timeline & Mistakes
            if (selectedTab == 2) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "⏳", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "तयारी वेळ-वेळापत्रक (टाइमलाइन)"
                                "hi" -> "तैयारी समय-सारणी (टाइमलाइन)"
                                else -> "Connected Preparation Timeline"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                itemsIndexed(festival.preparationTimeline) { index, task ->
                    val isLast = index == festival.preparationTimeline.size - 1

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        // Timeline Tree: Milestone Dot and Vertical Connecting Line
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(28.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(KesariyaSaffron)
                            )
                            if (!isLast) {
                                Box(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(72.dp)
                                        .background(KesariyaSaffron.copy(alpha = 0.3f))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 12.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = KesariyaSaffron.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = task.getTimeframe(language),
                                        color = KesariyaSaffron,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = task.getTask(language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🛡️", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "सहसा होणाऱ्या चुका व उपाय"
                                "hi" -> "अक्सर होने वाली गलतियां व समाधान"
                                else -> "Common Mistakes & Practical Solutions"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                items(festival.mistakes) { mistake ->
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Don't (Mistake)
                            Row(verticalAlignment = Alignment.Top) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = AuspiciousRed.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> "❌ टाळा"
                                            "hi" -> "❌ भूल"
                                            else -> "❌ Avoid"
                                        },
                                        color = AuspiciousRed,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = mistake.getMistake(language),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = AuspiciousRed,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 10.dp),
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )

                            // Do This (Solution)
                            Row(verticalAlignment = Alignment.Top) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = SuccessGreen.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> "✓ योग्य पद्धत"
                                            "hi" -> "✓ सही विधि"
                                            else -> "✓ Best Practice"
                                        },
                                        color = SuccessGreen,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = mistake.getSolution(language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // TAB 3: Smart Reminders
            if (selectedTab == 3) {
                item {
                    Text(
                        text = when (language) {
                            "mr" -> "स्मार्ट उत्सव रिमाइंडर"
                            "hi" -> "स्मार्ट उत्सव रिमाइंडर"
                            else -> "Smart Festival Reminders"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = when (language) {
                            "mr" -> "तयारी, खरेदी आणि शुभ मुहूर्त वेळेवर आपल्याला स्मरण करून देईल:"
                            "hi" -> "तैयारी, खरीदारी और शुभ मुहूर्त के समय पर आपको स्मरण दिलाएं:"
                            else -> "Never miss prep deadlines, shopping, and exact muhurat windows:"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            ReminderToggleRow(
                                title = when (language) {
                                    "mr" -> "तयारी स्मरण (१ दिवस आधी)"
                                    "hi" -> "तैयारी स्मरण (1 दिन पूर्व)"
                                    else -> "Preparation Alert (1 Day Before)"
                                },
                                subtitle = when (language) {
                                    "mr" -> "घराची स्वच्छता व चौरंग सजवण्याचे स्मरण"
                                    "hi" -> "घर की सफाई व चौकी सजाने का स्मरण"
                                    else -> "Reminds you to clean puja chowki and check idols"
                                },
                                checked = prepReminder,
                                onCheckedChange = {
                                    prepReminder = it
                                    prefs.setReminderActive(festival.id, "prep", it)
                                }
                            )

                            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                            ReminderToggleRow(
                                title = when (language) {
                                    "mr" -> "मुहूर्त स्मरण (३० मिनिटे आधी)"
                                    "hi" -> "मुहूर्त स्मरण (30 मिनट पहले)"
                                    else -> "Muhurat Alert (30 Mins Before)"
                                },
                                subtitle = when (language) {
                                    "mr" -> "शुभ मुहूर्त सुरू होण्यापूर्वी सावधान करेल"
                                    "hi" -> "शुभ मुहूर्त शुरू होने से पूर्व सचेत करें"
                                    else -> "Alerts you right before the auspicious window"
                                },
                                checked = muhuratReminder,
                                onCheckedChange = {
                                    muhuratReminder = it
                                    prefs.setReminderActive(festival.id, "muhurat", it)
                                }
                            )

                            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                            ReminderToggleRow(
                                title = when (language) {
                                    "mr" -> "साहित्य खरेदी स्मरण"
                                    "hi" -> "सामग्री खरीदारी स्मरण"
                                    else -> "Shopping Checklist Alert"
                                },
                                subtitle = when (language) {
                                    "mr" -> "ताजी फुले, फळे व प्रसाद साहित्य आणण्याची वेळ"
                                    "hi" -> "ताजे फूल, फल व प्रसाद सामग्री लाने का समय"
                                    else -> "Alert to procure fresh flowers, milk, and sweets"
                                },
                                checked = shoppingReminder,
                                onCheckedChange = {
                                    shoppingReminder = it
                                    prefs.setReminderActive(festival.id, "shopping", it)
                                }
                            )
                        }
                    }
                }

                item {
                    Button(
                        onClick = {
                            NotificationHelper.sendNotification(
                                context = context,
                                notificationId = festival.id.hashCode(),
                                title = when (language) {
                                    "mr" -> "🪔 ${festival.getName(language)} मुहूर्त स्मरण"
                                    "hi" -> "🪔 ${festival.nameHi} मुहूर्त स्मरण"
                                    else -> "🪔 ${festival.nameEn} Muhurat Alert"
                                },
                                message = when (language) {
                                    "mr" -> "शुभ मुहूर्त सुरू होण्यास ३० मिनिटे बाकी आहेत (${festival.muhurat.getTime(language)})। दीप प्रज्वलित करा."
                                    "hi" -> "शुभ मुहूर्त शुरू होने में 30 मिनट शेष हैं (${festival.muhurat.timeHi})। दीप प्रज्ज्वलित करें।"
                                    else -> "Auspicious muhurat starts in 30 minutes (${festival.muhurat.timeEn}). Prepare your diyas."
                                },
                                festivalId = festival.id
                            )
                            Toast.makeText(
                                context,
                                when (language) {
                                    "mr" -> "चाचणी सूचना पाठवली!"
                                    "hi" -> "परीक्षण सूचना भेजी गई!"
                                    else -> "Test notification sent!"
                                },
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "चाचणी नोटिफिकेशन पाठवा"
                                "hi" -> "परीक्षण नोटिफिकेशन भेजें"
                                else -> "Send Test Notification Now"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun ReminderToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
