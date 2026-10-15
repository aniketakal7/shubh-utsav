package com.shubhutsav.app.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight
import java.io.File
import java.io.FileOutputStream

data class GreetingFestivalOption(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val nameMr: String,
    val emoji: String,
    val defaultWishes: List<String>
)

data class GreetingTheme(
    val id: String,
    val name: String,
    val gradientColors: List<Color>,
    val textColor: Color,
    val accentColor: Color,
    val startColorInt: Int,
    val endColorInt: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingsMakerScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current

    val festivalOptions = remember {
        listOf(
            GreetingFestivalOption(
                id = "diwali",
                nameEn = "Diwali",
                nameHi = "दीपावली",
                nameMr = "दिवाळी",
                emoji = "🪔",
                defaultWishes = listOf(
                    "दीपावली के पावन पर्व पर आपके जीवन में सुख, शांति, समृद्धि और अपार खुशियाँ आएं। शुभ दीपावली!",
                    "दिवाळीच्या तेजोमय पर्वात तुमचे जीवन सुख, शांती व आनंदाने उजळून निघो. मंगलमय शुभेच्छा!",
                    "Wishing you and your family a blessed, prosperous, and joyful Deepavali filled with light!"
                )
            ),
            GreetingFestivalOption(
                id = "ganesh",
                nameEn = "Ganesh Chaturthi",
                nameHi = "गणेश चतुर्थी",
                nameMr = "गणेशोत्सव",
                emoji = "🐘",
                defaultWishes = listOf(
                    "भगवान श्री गणेश आपके समस्त विघ्नों को दूर कर सुख, शांति और ज्ञान का वरदान दें। गणपति बप्पा मोरया!",
                    "सर्व विघ्नांचे निवारण होऊन तुमच्या घरी सुख-समृद्धीचे आगमन होवो. गणेशोत्सवाच्या हार्दिक शुभेच्छा!",
                    "May Lord Ganesha remove all obstacles and bless you with wisdom, joy, and triumph!"
                )
            ),
            GreetingFestivalOption(
                id = "shivratri",
                nameEn = "Maha Shivratri",
                nameHi = "महाशिवरात्रि",
                nameMr = "महाशिवरात्री",
                emoji = "🔱",
                defaultWishes = listOf(
                    "महाकाल भगवान शिव और माता पार्वती की असीम कृपा आप पर सदैव बनी रहे। हर हर महादेव!",
                    "महादेवाच्या कृपेने तुमच्या सर्व मनोकामना पूर्ण होवोत. महाशिवरात्रीच्या मनःपूर्वक शुभेच्छा!",
                    "May the divine energy of Lord Shiva protect your home and illuminate your spiritual path."
                )
            ),
            GreetingFestivalOption(
                id = "navratri",
                nameEn = "Navratri & Dussehra",
                nameHi = "नवरात्रि व दशहरा",
                nameMr = "नवरात्रोत्सव व दसरा",
                emoji = "🚩",
                defaultWishes = listOf(
                    "माँ दुर्गा का नवस्वरूप आपके जीवन को आरोग्य, पराक्रम और सौभाग्य से परिपूर्ण करे। जय माता दी!",
                    "दुर्गा मातेच्या नऊ रूपांचा आशीर्वाद तुम्हास लाभो. दसऱ्याच्या सोनेरी शुभेच्छा!",
                    "May Goddess Durga empower you with courage, vitality, and boundless positivity!"
                )
            ),
            GreetingFestivalOption(
                id = "holi",
                nameEn = "Holi",
                nameHi = "होली",
                nameMr = "होळी",
                emoji = "🎨",
                defaultWishes = listOf(
                    "रंगों और खुशियों के महापर्व होली की आपको व आपके परिवार को हार्दिक शुभकामनाएँ!",
                    "रंगांच्या उत्सवात तुमच्या जीवनात प्रेमाचे आणि आनंदाचे रंग भरून जावोत. होळीच्या शुभेच्छा!",
                    "Wishing you a vibrant, joyful, and healthy festival of colors. Happy Holi!"
                )
            ),
            GreetingFestivalOption(
                id = "ram_navami",
                nameEn = "Ram Navami",
                nameHi = "राम नवमी",
                nameMr = "राम नवमी",
                emoji = "🏹",
                defaultWishes = listOf(
                    "मर्यादा पुरुषोत्तम भगवान श्री रामचंद्र के अवतरण दिवस की मंगलमयी शुभकामनाएँ। जय श्री राम!",
                    "श्रीराम नवमीच्या मंगल पर्वावर प्रभू श्रीरामांचे कृपाछत्र तुमच्यावर सदैव राहो. जय श्री राम!",
                    "May the ideals of Lord Rama inspire peace, justice, and devotion in your household."
                )
            ),
            GreetingFestivalOption(
                id = "janmashtami",
                nameEn = "Krishna Janmashtami",
                nameHi = "श्रीकृष्ण जन्माष्टमी",
                nameMr = "श्रीकृष्ण जन्माष्टमी",
                emoji = "🦚",
                defaultWishes = listOf(
                    "माखनचोर, नटखट नंदकिशोर आपके घर में खुशियाँ, प्रेम और मधुरता भर दें। राधे राधे!",
                    "बालगोपाळाच्या जन्मोत्सवाच्या आनंदमयी शुभेच्छा! कृष्ण कृपा सदैव तुमच्या पाठीशी राहो.",
                    "May Lord Krishna bless your life with divine joy, friendship, and inner harmony."
                )
            )
        )
    }

    val themes = remember {
        listOf(
            GreetingTheme(
                id = "royal_maroon",
                name = "Royal Maroon",
                gradientColors = listOf(Color(0xFF800000), Color(0xFF4A0000)),
                textColor = Color.White,
                accentColor = Color(0xFFFFD700),
                startColorInt = 0xFF800000.toInt(),
                endColorInt = 0xFF4A0000.toInt()
            ),
            GreetingTheme(
                id = "kesariya_gold",
                name = "Kesariya Glow",
                gradientColors = listOf(Color(0xFFFF8A00), Color(0xFFE52D27)),
                textColor = Color.White,
                accentColor = Color(0xFFFFF9C4),
                startColorInt = 0xFFFF8A00.toInt(),
                endColorInt = 0xFFE52D27.toInt()
            ),
            GreetingTheme(
                id = "midnight_divine",
                name = "Midnight Temple",
                gradientColors = listOf(Color(0xFF0F2027), Color(0xFF203A43)),
                textColor = Color.White,
                accentColor = Color(0xFFFFD54F),
                startColorInt = 0xFF0F2027.toInt(),
                endColorInt = 0xFF203A43.toInt()
            ),
            GreetingTheme(
                id = "vrindavan_green",
                name = "Vrindavan Green",
                gradientColors = listOf(Color(0xFF134E5E), Color(0xFF71B280)),
                textColor = Color.White,
                accentColor = Color(0xFFFFF176),
                startColorInt = 0xFF134E5E.toInt(),
                endColorInt = 0xFF71B280.toInt()
            )
        )
    }

    var selectedFestival by remember { mutableStateOf(festivalOptions.first()) }
    var selectedTheme by remember { mutableStateOf(themes.first()) }
    var senderName by remember { mutableStateOf("अनिकेत अकाल एवं परिवार") }
    var wishMessage by remember { mutableStateOf(selectedFestival.defaultWishes.first()) }

    // Update wish message when festival changes
    LaunchedEffect(selectedFestival, language) {
        val wishIndex = when (language) {
            "mr" -> 1
            "hi" -> 0
            else -> 2
        }.coerceIn(0, selectedFestival.defaultWishes.size - 1)
        wishMessage = selectedFestival.defaultWishes[wishIndex]
    }

    fun generateAndShareCard() {
        try {
            val width = 1080
            val height = 1080
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // Background Gradient
            val shader = LinearGradient(
                0f, 0f, 0f, height.toFloat(),
                selectedTheme.startColorInt,
                selectedTheme.endColorInt,
                Shader.TileMode.CLAMP
            )
            val bgPaint = Paint().apply { this.shader = shader }
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

            // Decorative Gold Border
            val borderPaint = Paint().apply {
                color = 0xFFFFD700.toInt()
                style = Paint.Style.STROKE
                strokeWidth = 14f
                isAntiAlias = true
            }
            canvas.drawRoundRect(RectF(40f, 40f, width - 40f, height - 40f), 32f, 32f, borderPaint)

            // Inner Thin Border
            val innerBorderPaint = Paint().apply {
                color = 0x88FFD700.toInt()
                style = Paint.Style.STROKE
                strokeWidth = 4f
                isAntiAlias = true
            }
            canvas.drawRoundRect(RectF(60f, 60f, width - 60f, height - 60f), 24f, 24f, innerBorderPaint)

            // Top App Watermark
            val topBrandPaint = Paint().apply {
                color = 0xCCFFD700.toInt()
                textSize = 34f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
            canvas.drawText("✨ शुभ उत्सव (Shubh Utsav) ✨", width / 2f, 130f, topBrandPaint)

            // Festival Emoji & Title
            val titlePaint = Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 66f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
            val festName = when (language) {
                "mr" -> selectedFestival.nameMr
                "hi" -> selectedFestival.nameHi
                else -> selectedFestival.nameEn
            }
            canvas.drawText("${selectedFestival.emoji} $festName ${selectedFestival.emoji}", width / 2f, 250f, titlePaint)

            // Gold divider line
            val divPaint = Paint().apply {
                color = 0xFFFFD700.toInt()
                strokeWidth = 4f
                isAntiAlias = true
            }
            canvas.drawLine(width / 2f - 240f, 300f, width / 2f + 240f, 300f, divPaint)

            // Blessings Text
            val wishPaint = Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 42f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            }

            // Word wrap wish message
            val words = wishMessage.split(" ")
            val lines = mutableListOf<String>()
            var currentLine = StringBuilder()
            for (word in words) {
                if (currentLine.length + word.length > 32) {
                    lines.add(currentLine.toString())
                    currentLine = StringBuilder(word).append(" ")
                } else {
                    currentLine.append(word).append(" ")
                }
            }
            if (currentLine.isNotEmpty()) lines.add(currentLine.toString())

            var startY = 440f
            for (line in lines) {
                canvas.drawText(line.trim(), width / 2f, startY, wishPaint)
                startY += 62f
            }

            // Bottom Traditional Signature Box
            val sigBgPaint = Paint().apply {
                color = 0x33000000
                style = Paint.Style.FILL
            }
            canvas.drawRoundRect(RectF(120f, 820f, width - 120f, 960f), 24f, 24f, sigBgPaint)

            val sigLabelPaint = Paint().apply {
                color = 0xFFFFD700.toInt()
                textSize = 32f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("शुभकामनाओं सहित :", width / 2f, 870f, sigLabelPaint)

            val sigNamePaint = Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 46f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
            canvas.drawText(senderName, width / 2f, 930f, sigNamePaint)

            // Save Bitmap to Cache & Share via FileProvider
            val cachePath = File(context.cacheDir, "greetings")
            cachePath.mkdirs()
            val file = File(cachePath, "festive_greeting_${System.currentTimeMillis()}.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val contentUri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                putExtra(
                    Intent.EXTRA_TEXT,
                    "$wishMessage\n\n— $senderName\n\nShared via Shubh Utsav (शुभ उत्सव) App"
                )
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Greeting Card via"))

        } catch (e: Exception) {
            Toast.makeText(context, "Error creating card: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "शुभेच्छा कार्ड मेकर"
                    "hi" -> "शुभकामना कार्ड मेकर"
                    else -> "Festive Card Maker"
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
            // Live Card Canvas Preview
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.Transparent,
                    shadowElevation = 12.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Brush.verticalGradient(selectedTheme.gradientColors))
                            .padding(18.dp)
                    ) {
                        // Decorative Gold Border Box
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Transparent)
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                // Header
                                Text(
                                    text = "✨ शुभ उत्सव (Shubh Utsav) ✨",
                                    fontSize = 12.sp,
                                    color = selectedTheme.accentColor,
                                    fontWeight = FontWeight.Bold
                                )

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "${selectedFestival.emoji} " + when (language) {
                                            "mr" -> selectedFestival.nameMr
                                            "hi" -> selectedFestival.nameHi
                                            else -> selectedFestival.nameEn
                                        } + " ${selectedFestival.emoji}",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = selectedTheme.textColor
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .height(2.dp)
                                            .background(selectedTheme.accentColor)
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = wishMessage,
                                        fontSize = 15.sp,
                                        lineHeight = 22.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = selectedTheme.textColor.copy(alpha = 0.95f),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }

                                // Bottom Signature Badge
                                Surface(
                                    shape = RoundedCornerShape(14.dp),
                                    color = Color.Black.copy(alpha = 0.25f),
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "शुभकामनाओं सहित :",
                                            fontSize = 11.sp,
                                            color = selectedTheme.accentColor
                                        )
                                        Text(
                                            text = senderName.ifBlank { "आपका परिवार" },
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = selectedTheme.textColor
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Big 1-Tap Share to WhatsApp Button
            item {
                Button(
                    onClick = { generateAndShareCard() },
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)), // WhatsApp Green
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = when (language) {
                            "mr" -> "व्हॉट्सअ‍ॅपवर कार्ड शेअर करा"
                            "hi" -> "व्हाट्सएप पर कार्ड शेयर करें"
                            else -> "Share Card to WhatsApp / Save"
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Select Festival Selector
            item {
                Text(
                    text = when (language) {
                        "mr" -> "उत्सव निवडा"
                        "hi" -> "त्योहार चुनें"
                        else -> "1. Choose Festival"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(festivalOptions) { fest ->
                        val isSelected = fest.id == selectedFestival.id
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedFestival = fest },
                            label = { Text("${fest.emoji} ${fest.nameHi}") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = KesariyaSaffron,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Select Card Color Theme
            item {
                Text(
                    text = when (language) {
                        "mr" -> "रंग थीम निवडा"
                        "hi" -> "कार्ड थीम चुनें"
                        else -> "2. Card Theme"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    themes.forEach { theme ->
                        val isSelected = theme.id == selectedTheme.id
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Brush.linearGradient(theme.gradientColors))
                                .clickable { selectedTheme = theme },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // Sender Name Input
            item {
                Text(
                    text = when (language) {
                        "mr" -> "तुमचे / कुटुंबाचे नाव"
                        "hi" -> "आपका / परिवार का नाम"
                        else -> "3. Your / Family Name"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = senderName,
                    onValueChange = { senderName = it },
                    label = { Text("Name on Card") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )
            }

            // Custom Message Input / Wish Templates
            item {
                Text(
                    text = when (language) {
                        "mr" -> "शुभेच्छा संदेश"
                        "hi" -> "शुभकामना संदेश"
                        else -> "4. Blessing Message"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = wishMessage,
                    onValueChange = { wishMessage = it },
                    label = { Text("Wish Message") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }
    }
}
