package com.shubhutsav.app.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary Sacred Palette - Royal Crimson & Deep Maroon
val RoyalMaroon = Color(0xFF6B1527)
val RoyalMaroonDark = Color(0xFF4A0E1A)
val RoyalMaroonLight = Color(0xFF8C2438)
val RoyalMaroonSurface = Color(0xFF591120)

// Secondary Sacred Palette - Radiant Kesariya Saffron
val KesariyaSaffron = Color(0xFFE26D19)
val KesariyaSaffronLight = Color(0xFFF98E41)
val KesariyaSaffronDark = Color(0xFFB54F08)
val KesariyaSaffronSubtle = Color(0xFFFFF3EB)

// Tertiary Sacred Palette - Luminous Vedic Gold & Amber
val VedicGold = Color(0xFFD4AF37)
val VedicGoldLight = Color(0xFFF7E2A3)
val VedicGoldDark = Color(0xFFAA820A)
val VedicGoldGlow = Color(0xFFFFE082)
val VedicGoldSubtle = Color(0xFFFFF9E6)

// Neutral & Background Surfaces - Warm Traditional Aesthetic
val WarmCreamBackground = Color(0xFFFCF8F3)
val WarmSandSurface = Color(0xFFF7EFE6)
val CardSurfaceWhite = Color(0xFFFFFFFF)
val CardBorderSubtle = Color(0xFFEFE4D6)
val CardBorderGold = Color(0xFFE6CCA0)

// Typography & Ink Tokens
val TextPrimaryInk = Color(0xFF261519)
val TextSecondaryMuted = Color(0xFF6E595D)
val TextTertiaryHint = Color(0xFF9E8B8F)

// Dark Mode Palette - Velvet Obsidian & Warm Amber
val DarkBackground = Color(0xFF140D10)
val DarkSurface = Color(0xFF1E1418)
val DarkSurfaceVariant = Color(0xFF2B1D23)
val DarkSurfaceElevated = Color(0xFF38262E)
val DarkCardBorder = Color(0xFF47313A)
val DarkCardBorderGold = Color(0xFF6E5338)

val DarkTextPrimary = Color(0xFFF7EEF0)
val DarkTextSecondary = Color(0xFFBEADB2)
val DarkTextTertiary = Color(0xFF8C7980)

// Auspicious Status & Timing Colors
val SuccessGreen = Color(0xFF2E7D32)
val SuccessGreenLight = Color(0xFFE8F5E9)
val SuccessGreenBorder = Color(0xFFA5D6A7)

val AuspiciousRed = Color(0xFFC62828)
val AuspiciousRedLight = Color(0xFFFFEBEE)
val AuspiciousRedBorder = Color(0xFFEF9A9A)

val CalmTeal = Color(0xFF00796B)
val CalmTealLight = Color(0xFFE0F2F1)

val RahuKaalWarning = Color(0xFFD32F2F)
val AbhijitAuspicious = Color(0xFF2E7D32)

// Premium Sacred Gradients
val MaroonGradient = Brush.verticalGradient(
    colors = listOf(RoyalMaroonLight, RoyalMaroonDark)
)

val PanchangCardGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF631324), Color(0xFF3F0B16))
)

val SaffronAccentGradient = Brush.horizontalGradient(
    colors = listOf(KesariyaSaffron, KesariyaSaffronLight)
)

val GoldAccentGradient = Brush.horizontalGradient(
    colors = listOf(VedicGoldDark, VedicGoldLight, VedicGold)
)

val GoldenShimmerGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFFFD54F), Color(0xFFFFECB3), Color(0xFFFFCA28))
)

val WarmCardGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFAF4EC))
)
