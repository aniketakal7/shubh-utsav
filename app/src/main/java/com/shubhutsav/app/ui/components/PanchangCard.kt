package com.shubhutsav.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.EditLocation
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhutsav.app.data.City
import com.shubhutsav.app.data.PanchangCalculator
import com.shubhutsav.app.ui.theme.AbhijitAuspicious
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.PanchangCardGradient
import com.shubhutsav.app.ui.theme.RahuKaalWarning
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.RoyalMaroonDark
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldLight

@Composable
fun PanchangCard(
    city: City,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onChangeCityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val panchang = remember(city) { PanchangCalculator.calculateForDate(city = city) }
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(28.dp),
        color = RoyalMaroon,
        shadowElevation = 12.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PanchangCardGradient)
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-50).dp)
                    .clip(CircleShape)
                    .background(Color(0x33FFD54F))
            )
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = (-30).dp, y = 30.dp)
                    .clip(CircleShape)
                    .background(Color(0x22FF8A50))
            )
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header: Date, Sacred Title & City
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isHindi) "दैनिक वैदिक पंचांग" else "Daily Vedic Panchang",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "✨", fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (isHindi) panchang.gregorianDateHi else panchang.gregorianDateEn,
                        color = VedicGoldLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // City badge with change action
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White.copy(alpha = 0.12f),
                    border = BorderStroke(0.8.dp, Color.White.copy(alpha = 0.25f)),
                    modifier = Modifier
                        .heightIn(min = 36.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(onClick = onChangeCityClick)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.EditLocation,
                            contentDescription = "Change City",
                            tint = VedicGold,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isHindi) city.hindiName else city.name,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = 88.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Primary Panchang Elements (Tithi, Nakshatra, Day & Month)
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.Black.copy(alpha = 0.2f),
                border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.1f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PanchangItem(
                        label = if (isHindi) "तिथि" else "Tithi",
                        value = if (isHindi) panchang.tithiHi else panchang.tithiEn,
                        subtext = if (isHindi) panchang.pakshaHi else panchang.pakshaEn,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(44.dp)
                            .background(Color.White.copy(alpha = 0.15f))
                    )

                    PanchangItem(
                        label = if (isHindi) "नक्षत्र" else "Nakshatra",
                        value = if (isHindi) panchang.nakshatraHi else panchang.nakshatraEn,
                        subtext = null,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    )

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(44.dp)
                            .background(Color.White.copy(alpha = 0.15f))
                    )

                    PanchangItem(
                        label = if (isHindi) "वार / मास" else "Day & Month",
                        value = if (isHindi) panchang.weekdayHi else panchang.weekdayEn,
                        subtext = if (isHindi) panchang.hinduMonthHi else panchang.hinduMonthEn,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sunrise & Sunset Pill Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.1f),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🌅", fontSize = 15.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = if (isHindi) "सूर्योदय" else "Sunrise",
                                color = VedicGoldLight,
                                fontSize = 10.sp
                            )
                            Text(
                                text = panchang.sunrise,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.1f),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🌇", fontSize = 15.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = if (isHindi) "सूर्यास्त" else "Sunset",
                                color = VedicGoldLight,
                                fontSize = 10.sp
                            )
                            Text(
                                text = panchang.sunset,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Key Muhurats Showcase: Abhijit (Auspicious) vs Rahu Kaal (Caution)
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.Black.copy(alpha = 0.35f),
                border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.12f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Abhijit Muhurat (Auspicious)
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(7.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF81C784))
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isHindi) "अभिजीत (शुभ मुहूर्त)" else "Abhijit (Auspicious)",
                                    color = Color(0xFFA5D6A7),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = panchang.abhijitMuhurat,
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(36.dp)
                                .background(Color.White.copy(alpha = 0.15f))
                        )

                        // Rahu Kaal (Caution)
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(7.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFF8A80))
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isHindi) "राहु काल (वर्जित)" else "Rahu Kaal (Avoid)",
                                    color = Color(0xFFFF8A80),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = panchang.rahuKaal,
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Expandable Details (Yoga, Karana, Brahma Muhurat)
                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            HorizontalDivider(
                                color = Color.White.copy(alpha = 0.12f),
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isHindi) "योग" else "Yoga",
                                        color = VedicGoldLight,
                                        fontSize = 10.sp
                                    )
                                    Text(
                                        text = if (isHindi) panchang.yogaHi else panchang.yogaEn,
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isHindi) "करण" else "Karana",
                                        color = VedicGoldLight,
                                        fontSize = 10.sp
                                    )
                                    Text(
                                        text = if (isHindi) panchang.karanaHi else panchang.karanaEn,
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Column(
                                    modifier = Modifier.weight(1.2f),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = if (isHindi) "ब्रह्म मुहूर्त" else "Brahma Muhurat",
                                        color = VedicGoldLight,
                                        fontSize = 10.sp
                                    )
                                    Text(
                                        text = panchang.brahmaMuhurat,
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Expand / Collapse Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 44.dp)
                    .clickable { isExpanded = !isExpanded }
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isExpanded) {
                        if (isHindi) "संक्षिप्त विवरण" else "Show Less"
                    } else {
                        if (isHindi) "विस्तृत मुहूर्त व योग देखें" else "View More Muhurats & Yoga"
                    },
                    color = VedicGoldLight,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = VedicGoldLight,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        }
    }
}

@Composable
private fun PanchangItem(
    label: String,
    value: String,
    subtext: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = VedicGoldLight,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        if (subtext != null) {
            Text(
                text = subtext,
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 10.sp
            )
        }
    }
}
