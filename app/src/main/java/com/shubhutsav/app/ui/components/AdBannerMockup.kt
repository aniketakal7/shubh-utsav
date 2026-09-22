package com.shubhutsav.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight

@Composable
fun AdBannerMockup(
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onRemoveAdsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                ) {
                    Text(
                        text = "AD",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = when (language) {
                            "mr" -> "शुभ उत्सव मोफत आवृत्ती"
                            "hi" -> "शुभ उत्सव निःशुल्क संस्करण"
                            else -> "Shubh Utsav Free Edition"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = when (language) {
                            "mr" -> "शांत आणि जाहिरातमुक्त अनुभवासाठी अपग्रेड करा"
                            "hi" -> "शांत एवं विज्ञापन-मुक्त अनुभव के लिए अपग्रेड करें"
                            else -> "Upgrade for clean, peaceful ad-free companion"
                        },
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = KesariyaSaffron.copy(alpha = 0.12f),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onRemoveAdsClick)
            ) {
                Text(
                    text = when (language) {
                        "mr" -> "जाहिराती हटवा"
                        "hi" -> "विज्ञापन हटाएं"
                        else -> "Remove Ads"
                    },
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = KesariyaSaffron,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun PremiumUpgradeDialog(
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onDismiss: () -> Unit,
    onUpgrade: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(26.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.4f)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gold star glowing halo
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(VedicGold.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = VedicGold,
                        modifier = Modifier.size(34.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = when (language) {
                        "mr" -> "शुभ उत्सव प्रीमियम"
                        "hi" -> "शुभ उत्सव प्रीमियम"
                        else -> "Shubh Utsav Premium"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = when (language) {
                        "mr" -> "एक वेळचे आजीवन अपग्रेड — शांत, सुंदर आणि जाहिरातमुक्त अनुभव."
                        "hi" -> "एक बार का आजीवन अपग्रेड — शांत, सुंदर और विज्ञापन-मुक्त अनुभव।"
                        else -> "One-time lifetime upgrade — pure, ad-free sacred companion."
                    },
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(18.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PremiumFeatureItem(
                        icon = "✨",
                        title = when (language) {
                            "mr" -> "१००% जाहिरातमुक्त अनुभव"
                            "hi" -> "100% विज्ञापन-मुक्त अनुभव"
                            else -> "100% Ad-Free Experience"
                        },
                        desc = when (language) {
                            "mr" -> "पूजा आणि विधी दरम्यान कोणतीही अडचण किंवा जाहिरात नाही"
                            "hi" -> "पूजा और विधि के समय कोई रुकावट या विज्ञापन नहीं"
                            else -> "Zero distractions during sacred rituals"
                        }
                    )
                    PremiumFeatureItem(
                        icon = "📶",
                        title = when (language) {
                            "mr" -> "पूर्ण ऑफलाइन अनुभव"
                            "hi" -> "पूर्ण ऑफलाइन एक्सेस"
                            else -> "Full Offline Companion"
                        },
                        desc = when (language) {
                            "mr" -> "सर्व ३०+ उत्सव, विधी व मंत्र इंटरनेटशिवाय उपलब्ध"
                            "hi" -> "सभी 30+ उत्सव, विधि व मंत्र बिना इंटरनेट के उपलब्ध"
                            else -> "All festivals and mantras always available offline"
                        }
                    )
                    PremiumFeatureItem(
                        icon = "🔔",
                        title = when (language) {
                            "mr" -> "स्मार्ट मुहूर्त व तयारी स्मरण"
                            "hi" -> "स्मार्ट मुहूर्त व तैयारी स्मरण"
                            else -> "Smart Muhurat & Prep Alerts"
                        },
                        desc = when (language) {
                            "mr" -> "पूजेच्या बरोबर ३० मिनिटे आधी अचूक रिमाइंडर"
                            "hi" -> "पूजा से ठीक 30 मिनट पहले सटीक रिमाइंडर"
                            else -> "Timely alerts 30 minutes before muhurat"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onUpgrade,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = when (language) {
                            "mr" -> "₹९९ / आजीवन अपग्रेड"
                            "hi" -> "₹99 / आजीवन अपग्रेड"
                            else -> "Get Lifetime Access (₹99 / $1.99)"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onDismiss) {
                    Text(
                        text = when (language) {
                            "mr" -> "नंतर विचार करू"
                            "hi" -> "बाद में विचार करेंगे"
                            else -> "Maybe Later"
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumFeatureItem(
    icon: String,
    title: String,
    desc: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = icon, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(text = desc, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
