package com.shubhutsav.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.FestivalRepository
import com.shubhutsav.app.ui.components.AppBottomNav
import com.shubhutsav.app.ui.components.AppDest
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.components.CityPickerDialog
import com.shubhutsav.app.ui.components.EmojiWell
import com.shubhutsav.app.ui.components.FestivalMiniCard
import com.shubhutsav.app.ui.components.PanchangCard
import com.shubhutsav.app.ui.components.SectionHeader
import com.shubhutsav.app.ui.theme.GreetingWashGradient
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.VedicGold
import java.util.Calendar

@Composable
fun HomeScreen(
    navController: NavHostController,
    city: City,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onToggleLanguage: () -> Unit = {},
    onLanguageChange: (String) -> Unit = {},
    onCityChanged: (City) -> Unit
) {
    var showCityPicker by remember { mutableStateOf(false) }
    val upcomingFestivals = remember { FestivalRepository.festivals.take(8) }

    // Dynamic Time-aware Vedic Greeting with Marathi, Hindi & English
    val greetingInfo = remember(language) {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        when (hour) {
            in 4..11 -> when (language) {
                "mr" -> Pair("नमस्ते 🙏 शुभ प्रभात", "आजचा पावन दिवस तुमच्यासाठी मंगलमय असो")
                "hi" -> Pair("नमस्ते 🙏 शुभ प्रभात", "आज का पावन दिन आपके लिए मंगलमय हो")
                else -> Pair("Namaste 🙏 Good Morning", "May your morning be peaceful & blessed")
            }
            in 12..16 -> when (language) {
                "mr" -> Pair("नमस्ते 🙏 शुभ दुपार", "दैनिक पंचांग व शुभ मुहूर्त पहा")
                "hi" -> Pair("नमस्ते 🙏 शुभ मध्याह्न", "दैनिक पंचांग व शुभ मुहूर्त देखें")
                else -> Pair("Namaste 🙏 Good Afternoon", "Check today's panchang and timings")
            }
            in 17..20 -> when (language) {
                "mr" -> Pair("नमस्ते 🙏 शुभ संध्याकाळ (दीपवेळ)", "संध्याकाळी पवित्र दीप प्रज्वलित करा")
                "hi" -> Pair("नमस्ते 🙏 शुभ संध्या (दीप वेला)", "संध्या काल में दीप प्रज्ज्वलित करें")
                else -> Pair("Namaste 🙏 Auspicious Evening", "Time to light the sacred evening lamp")
            }
            else -> when (language) {
                "mr" -> Pair("नमस्ते 🙏 शुभ रात्री", "उद्याच्या शुभ मुहूर्ताची तयारी करा")
                "hi" -> Pair("नमस्ते 🙏 शुभ रात्रि", "कल के शुभ मुहूर्त की तैयारी करें")
                else -> Pair("Namaste 🙏 Peaceful Night", "Rest peacefully and plan tomorrow's pujas")
            }
        }
    }

    if (showCityPicker) {
        CityPickerDialog(
            currentCityId = city.id,
            isHindi = language == "hi",
            language = language,
            onCitySelected = onCityChanged,
            onDismiss = { showCityPicker = false }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "शुभ उत्सव"
                    "hi" -> "शुभ उत्सव"
                    else -> "Shubh Utsav"
                },
                language = language,
                onLanguageChange = onLanguageChange,
                isHindi = language != "en",
                onToggleLanguage = onToggleLanguage,
                cityName = if (language == "en") city.name else city.hindiName,
                onCityClick = { showCityPicker = true },
                onSettingsClick = { navController.navigate("settings") }
            )
        },
        containerColor = Color.Transparent,
        bottomBar = {
            AppBottomNav(
                selected = AppDest.Home,
                language = language,
                onHome = {},
                onCalendar = { navController.navigate("calendar") },
                onSettings = { navController.navigate("settings") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .background(GreetingWashGradient)
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = greetingInfo.first,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = greetingInfo.second,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        EmojiWell(emoji = "🪔", tint = KesariyaSaffron, size = 56.dp, emojiSize = 28)
                    }
                }
            }

            // Real-Time Astronomical Panchang Dashboard Card
            item {
                PanchangCard(
                    city = city,
                    isHindi = language != "en",
                    language = language,
                    onChangeCityClick = { showCityPicker = true }
                )
            }

            item {
                SectionHeader(
                    emoji = "🎉",
                    title = when (language) {
                        "mr" -> "येणारे मुख्य उत्सव"
                        "hi" -> "आने वाले मुख्य उत्सव"
                        else -> "Upcoming Celebrations"
                    },
                    actionLabel = when (language) {
                        "mr" -> "सर्व पहा"
                        "hi" -> "सभी देखें"
                        else -> "View all"
                    },
                    onAction = { navController.navigate("calendar") }
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(upcomingFestivals) { festival ->
                        FestivalMiniCard(
                            festival = festival,
                            isHindi = language != "en",
                            language = language,
                            onClick = { navController.navigate("festival/${festival.id}") }
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    emoji = "⚡",
                    title = when (language) {
                        "mr" -> "जलद मार्गदर्शिका"
                        "hi" -> "त्वरित मार्गदर्शिका"
                        else -> "Quick Actions"
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HomeActionCard(
                        icon = "📅",
                        title = when (language) {
                            "mr" -> "उत्सव दिनदर्शिका"
                            "hi" -> "उत्सव कैलेंडर"
                            else -> "Festival Calendar"
                        },
                        subtitle = when (language) {
                            "mr" -> "३०+ प्रमुख सण व मुहूर्त"
                            "hi" -> "30+ प्रमुख पर्व व मुहूर्त"
                            else -> "All 30+ Sacred Festivals"
                        },
                        accentColor = RoyalMaroon,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("calendar") }
                    )

                    HomeActionCard(
                        icon = "🛒",
                        title = when (language) {
                            "mr" -> "पूजा साहित्य यादी"
                            "hi" -> "पूजा सामग्री सूची"
                            else -> "Shopping Lists"
                        },
                        subtitle = when (language) {
                            "mr" -> "यादी व व्हॉट्सअ‍ॅपवर शेअर"
                            "hi" -> "चेकलिस्ट व शेयर करें"
                            else -> "Samagri & WhatsApp Share"
                        },
                        accentColor = KesariyaSaffron,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("shopping/diwali") }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HomeActionCard(
                        icon = "🪔",
                        title = when (language) {
                            "mr" -> "दिवाळी पूजा विधी"
                            "hi" -> "दीपावली पूजा विधि"
                            else -> "Diwali Puja Vidhi"
                        },
                        subtitle = when (language) {
                            "mr" -> "टप्प्याटप्प्याने संपूर्ण विधी"
                            "hi" -> "चरण-दर-चरण संपूर्ण विधि"
                            else -> "Step-by-step Lakshmi Puja"
                        },
                        accentColor = VedicGold,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("festival/diwali") }
                    )

                    HomeActionCard(
                        icon = "🔱",
                        title = when (language) {
                            "mr" -> "महाशिवरात्री विधी"
                            "hi" -> "महाशिवरात्रि विधि"
                            else -> "Shivratri Puja"
                        },
                        subtitle = when (language) {
                            "mr" -> "चार प्रहर अभिषेक व मंत्र"
                            "hi" -> "चार प्रहर अभिषेक व मंत्र"
                            else -> "Abhishekam & Mantras"
                        },
                        accentColor = RoyalMaroon,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("festival/shivratri") }
                    )
                }
            }

            // Daily Gentle Cultural & Practical Tip
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        EmojiWell(emoji = "💡", tint = KesariyaSaffron, size = 44.dp, emojiSize = 20)
                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = when (language) {
                                    "mr" -> "आजचा व्यावहारिक सल्ला"
                                    "hi" -> "आज का व्यावहारिक सुझाव"
                                    else -> "Daily Practical Household Tip"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "पूजा साहित्य २ दिवस आधी गोळा केल्याने पूजेच्या दिवशी मन शांत व एकाग्र राहते. शुभ उत्सव अ‍ॅपची यादी वापरा — हे इंटरनेटशिवायही पूर्णपणे चालते."
                                    "hi" -> "पूजा सामग्री 2 दिन पहले एकत्र कर लेने से पूजा के दिन मन एकाग्र और शांत रहता है। शुभ उत्सव की चेकलिस्ट का उपयोग करें — यह बिना इंटरनेट के भी पूरी तरह काम करती है।"
                                    else -> "Assembling your puja samagri 2 days early prevents last-minute rush during auspicious muhurat. All checklists in Shubh Utsav stay saved completely offline."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun HomeActionCard(
    icon: String,
    title: String,
    subtitle: String,
    accentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp,
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            EmojiWell(emoji = icon, tint = accentColor, size = 48.dp, emojiSize = 24)
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = subtitle,
                fontSize = 11.sp,
                lineHeight = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
        }
    }
}

