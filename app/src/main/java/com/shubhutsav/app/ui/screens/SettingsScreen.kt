package com.shubhutsav.app.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.UpdateCheckResult
import com.shubhutsav.app.data.UpdateInfo
import com.shubhutsav.app.data.UpdateManager
import com.shubhutsav.app.notifications.NotificationHelper
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.components.CityPickerDialog
import com.shubhutsav.app.ui.components.PremiumUpgradeDialog
import com.shubhutsav.app.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavHostController,
    city: City,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    isDark: Boolean,
    isPremium: Boolean,
    onLanguageChange: (String) -> Unit = {},
    onToggleLanguage: () -> Unit = {},
    onToggleDark: (Boolean) -> Unit,
    onCityChanged: (City) -> Unit,
    onUpgradePremium: () -> Unit,
    onShowUpdateDialog: (UpdateInfo) -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }

    var showCityPicker by remember { mutableStateOf(false) }
    var showPremiumDialog by remember { mutableStateOf(false) }
    var remindersEnabled by remember { mutableStateOf(prefs.remindersEnabled) }
    var ritualStyle by remember { mutableStateOf(prefs.ritualStyle) }

    val coroutineScope = rememberCoroutineScope()
    var isCheckingUpdate by remember { mutableStateOf(false) }
    val currentVersionName = remember { UpdateManager.getCurrentVersionName(context) }
    val currentVersionCode = remember { UpdateManager.getCurrentVersionCode(context) }

    if (showCityPicker) {
        CityPickerDialog(
            currentCityId = city.id,
            isHindi = language == "hi",
            language = language,
            onCitySelected = onCityChanged,
            onDismiss = { showCityPicker = false }
        )
    }

    if (showPremiumDialog) {
        PremiumUpgradeDialog(
            isHindi = language == "hi",
            language = language,
            onDismiss = { showPremiumDialog = false },
            onUpgrade = {
                onUpgradePremium()
                showPremiumDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "सेटिंग्ज"
                    "hi" -> "सेटिंग्स"
                    else -> "Settings"
                },
                isHindi = language == "hi",
                language = language,
                onLanguageChange = onLanguageChange,
                onToggleLanguage = onToggleLanguage,
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "मुख्य"
                                    "hi" -> "मुख्य"
                                    else -> "Home"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("calendar") },
                        icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Calendar") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "कॅलेंडर"
                                    "hi" -> "कैलेंडर"
                                    else -> "Calendar"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Already in Settings */ },
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "सेटिंग्ज"
                                    "hi" -> "सेटिंग्स"
                                    else -> "Settings"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(vertical = 12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(2.dp))

            // Premium Membership Status Showcase Card
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isPremium) RoyalMaroon else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    1.dp,
                    if (isPremium) VedicGold.copy(alpha = 0.6f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.8f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { if (!isPremium) showPremiumDialog = true }
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                if (isPremium) Color.White.copy(alpha = 0.15f) else KesariyaSaffron.copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = if (isPremium) "🌟" else "✨", fontSize = 26.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isPremium)
                                (when (language) {
                                    "mr" -> "शुभ उत्सव प्रीमियम सक्रिय"
                                    "hi" -> "शुभ उत्सव प्रीमियम सक्रिय"
                                    else -> "Shubh Utsav Premium Active"
                                })
                            else
                                (when (language) {
                                    "mr" -> "जाहिराती हटवा (आजीवन प्रीमियम)"
                                    "hi" -> "विज्ञापन हटाएं (आजीवन प्रीमियम)"
                                    else -> "Remove Ads (Lifetime Premium)"
                                }),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isPremium) Color.White else MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isPremium)
                                (when (language) {
                                    "mr" -> "१००% जाहिरातमुक्त पवित्र अनुभव"
                                    "hi" -> "100% विज्ञापन-मुक्त पवित्र अनुभव"
                                    else -> "100% pure ad-free experience"
                                })
                            else
                                (when (language) {
                                    "mr" -> "केवळ ₹९९ मध्ये अखंड पूजेचा आनंद घ्या"
                                    "hi" -> "मात्र ₹99 में बिना रुकावट पूजा का आनंद लें"
                                    else -> "One-time upgrade for pure focus (₹99)"
                                }),
                            fontSize = 12.sp,
                            color = if (isPremium) VedicGoldLight else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    if (!isPremium) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = KesariyaSaffron
                        ) {
                            Text(
                                text = when (language) {
                                    "mr" -> "अपग्रेड"
                                    "hi" -> "अपग्रेड"
                                    else -> "Upgrade"
                                },
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }

            // General Preferences Section
            Text(
                text = when (language) {
                    "mr" -> "अनुभव आणि प्राधान्ये"
                    "hi" -> "अनुभव एवं प्राथमिकताएं"
                    else -> "Experience Preferences"
                },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Language Selection Row
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(KesariyaSaffron.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(imageVector = Icons.Default.Language, contentDescription = null, tint = KesariyaSaffron, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = when (language) {
                                        "mr" -> "अ‍ॅपची भाषा"
                                        "hi" -> "ऐप की भाषा"
                                        else -> "App Language"
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = when (language) {
                                        "mr" -> "मराठी, हिंदी आणि इंग्रजी उपलब्ध"
                                        "hi" -> "हिंदी, मराठी और अंग्रेजी उपलब्ध"
                                        else -> "English, Hindi and Marathi available"
                                    },
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val langOptions = listOf(
                                Triple("en", "English", "EN"),
                                Triple("hi", "हिंदी", "HI"),
                                Triple("mr", "मराठी", "MR")
                            )
                            langOptions.forEach { (code, label, badge) ->
                                val isSelected = language == code
                                FilterChip(
                                    selected = isSelected,
                                    onClick = {
                                        onLanguageChange(code)
                                    },
                                    leadingIcon = {
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                                        ) {
                                            Text(
                                                text = badge,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                    },
                                    label = {
                                        Text(
                                            text = label,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // Dark Mode Switch Row
                    SettingSwitchRow(
                        icon = Icons.Default.DarkMode,
                        iconTint = Color(0xFF7E57C2),
                        title = when (language) {
                            "mr" -> "डार्क मोड (रात्र स्वरूप)"
                            "hi" -> "डार्क मोड (रात्रि स्वरूप)"
                            else -> "Dark Mode"
                        },
                        subtitle = when (language) {
                            "mr" -> "रात्रीच्या वेळी डोळ्यांसाठी सौम्य थीम"
                            "hi" -> "रात्रि के समय आंखों के लिए सौम्य थीम"
                            else -> "Gentle velvet nighttime appearance"
                        },
                        checked = isDark,
                        onCheckedChange = onToggleDark
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // City Location Selector Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { showCityPicker = true }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(VedicGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = VedicGold, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = when (language) {
                                    "mr" -> "शहर / ठिकाण"
                                    "hi" -> "शहर / स्थान"
                                    else -> "City & Panchang Location"
                                },
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "${if (language == "en") city.name else city.hindiName} (${if (language == "en") city.state else city.hindiState})",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = KesariyaSaffron.copy(alpha = 0.12f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MyLocation,
                                        contentDescription = null,
                                        tint = KesariyaSaffron,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = "GPS",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KesariyaSaffron
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (language) {
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

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // Ritual Tradition / Style
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(CalmTeal.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(imageVector = Icons.Default.Tune, contentDescription = null, tint = CalmTeal, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = when (language) {
                                        "mr" -> "पूजा परंपरा पद्धत"
                                        "hi" -> "पूजा परंपरा शैली"
                                        else -> "Ritual Tradition Style"
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(text = ritualStyle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("General", "North Indian", "South Indian").forEach { st ->
                                val label = when (st) {
                                    "General" -> when (language) { "mr" -> "सामान्य"; "hi" -> "सामान्य"; else -> "General" }
                                    "North Indian" -> when (language) { "mr" -> "उत्तर भारतीय"; "hi" -> "उत्तर भारतीय"; else -> "North Indian" }
                                    "South Indian" -> when (language) { "mr" -> "दक्षिण भारतीय"; "hi" -> "दक्षिण भारतीय"; else -> "South Indian" }
                                    else -> st
                                }
                                FilterChip(
                                    selected = ritualStyle == st,
                                    onClick = {
                                        ritualStyle = st
                                        prefs.ritualStyle = st
                                    },
                                    label = { Text(label, fontSize = 11.sp, fontWeight = if (ritualStyle == st) FontWeight.Bold else FontWeight.Medium) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Notifications & Reminders Section
            Text(
                text = when (language) {
                    "mr" -> "सूचना आणि मुहूर्त स्मरणपत्रे"
                    "hi" -> "सूचनाएं एवं मुहूर्त रिमाइंडर"
                    else -> "Notifications & Timing Alerts"
                },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SettingSwitchRow(
                        icon = Icons.Default.Notifications,
                        iconTint = AuspiciousRed,
                        title = when (language) {
                            "mr" -> "उत्सव व मुहूर्त स्मरणपत्र"
                            "hi" -> "उत्सव व मुहूर्त रिमाइंडर"
                            else -> "Festival & Muhurat Alerts"
                        },
                        subtitle = when (language) {
                            "mr" -> "तयारी व शुभ मुहूर्त सुरू होण्यापूर्वी सूचना"
                            "hi" -> "तैयारी व शुभ मुहूर्त शुरू होने से पूर्व सूचनाएं"
                            else -> "Preparation, shopping and timing notifications"
                        },
                        checked = remindersEnabled,
                        onCheckedChange = {
                            remindersEnabled = it
                            prefs.remindersEnabled = it
                        }
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedButton(
                        onClick = {
                            NotificationHelper.sendTestReminder(context, isHindi = language == "hi", language = language)
                            Toast.makeText(
                                context,
                                when (language) {
                                    "mr" -> "चाचणी सूचना पाठवली!"
                                    "hi" -> "परीक्षण नोटिफिकेशन भेजा गया!"
                                    else -> "Test notification sent!"
                                },
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.NotificationsActive, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "चाचणी सूचना पाठवा"
                                "hi" -> "परीक्षण रिमाइंडर भेजें"
                                else -> "Send Test Notification Now"
                            },
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // App Info Card
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🪔", fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Shubh Utsav (शुभ उत्सव)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "v$currentVersionName (Build $currentVersionCode) • Offline Ready",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // In-App Update Actions
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SystemUpdate,
                                        contentDescription = null,
                                        tint = KesariyaSaffron,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = when (language) {
                                            "mr" -> "अ‍ॅप अद्यतने (Updates)"
                                            "hi" -> "ऐप अपडेट (Updates)"
                                            else -> "App Updates"
                                        },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }

                                if (isCheckingUpdate) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp,
                                        color = KesariyaSaffron
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Check for Updates Button
                                Button(
                                    onClick = {
                                        if (isCheckingUpdate) return@Button
                                        coroutineScope.launch {
                                            isCheckingUpdate = true
                                            val result = UpdateManager.checkForUpdates(
                                                context = context,
                                                endpointUrl = prefs.customUpdateEndpointUrl
                                            )
                                            isCheckingUpdate = false
                                            when (result) {
                                                is UpdateCheckResult.UpdateAvailable -> {
                                                    onShowUpdateDialog(result.info)
                                                }
                                                is UpdateCheckResult.UpToDate -> {
                                                    Toast.makeText(
                                                        context,
                                                        when (language) {
                                                            "mr" -> "आपण आधीच नवीनतम आवृत्ती (v${result.currentVersionName}) वापरत आहात! 🎉"
                                                            "hi" -> "आप पहले से ही नवीनतम संस्करण (v${result.currentVersionName}) पर हैं! 🎉"
                                                            else -> "You're already on the latest version (v${result.currentVersionName})! 🎉"
                                                        },
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                                is UpdateCheckResult.Error -> {
                                                    Toast.makeText(
                                                        context,
                                                        when (language) {
                                                            "mr" -> "अद्यतन तपासता आले नाही. कृपया इंटरनेट तपासा."
                                                            "hi" -> "अपडेट नहीं देखा जा सका। कृपया इंटरनेट जांचें।"
                                                            else -> "Could not check for updates. Please check internet connection."
                                                        },
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 10.dp)
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> "अद्यतने तपासा"
                                            "hi" -> "अपडेट जांचें"
                                            else -> "Check Update"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                // Preview Update Screen Button (for developer / testing)
                                OutlinedButton(
                                    onClick = {
                                        onShowUpdateDialog(UpdateInfo.SAMPLE_UPDATE)
                                    },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 10.dp)
                                ) {
                                    Text(
                                        text = when (language) {
                                            "mr" -> "पूर्वावलोकन"
                                            "hi" -> "पूर्वावलोकन"
                                            else -> "Preview UI"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = when (language) {
                            "mr" -> "शुभ उत्सवचा उद्देश प्रत्येक कुटुंबाला घरी आत्मविश्वासाने आणि शांततेने पूजा व सण साजरे करण्यास मदत करणे हा आहे."
                            "hi" -> "शुभ उत्सव का उद्देश्य हर परिवार को घर पर आत्मविश्वास और शांति से पूजा व त्योहार मनाने में सहयोग देना है।"
                            else -> "Shubh Utsav helps young couples and busy families prepare and celebrate festivals at home with peace, clarity and devotion."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, "Celebrate festivals and perform pujas with complete peace of mind! Download Shubh Utsav app.")
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share App"))
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                when (language) {
                                    "mr" -> "अ‍ॅप शेअर करा"
                                    "hi" -> "ऐप शेयर करें"
                                    else -> "Share App"
                                },
                                fontSize = 12.sp
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    when (language) {
                                        "mr" -> "अभिप्रायाबद्दल धन्यवाद!"
                                        "hi" -> "फीडबैक हेतु धन्यवाद!"
                                        else -> "Thank you for supporting Shubh Utsav!"
                                    },
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                when (language) {
                                    "mr" -> "रेटिंग द्या"
                                    "hi" -> "रेटिंग दें"
                                    else -> "Rate App"
                                },
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SettingSwitchRow(
    icon: ImageVector,
    iconTint: Color,
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f).padding(end = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(text = subtitle, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
