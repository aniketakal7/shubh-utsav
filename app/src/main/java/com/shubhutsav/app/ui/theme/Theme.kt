package com.shubhutsav.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = VedicGold,
    onPrimary = DarkBackground,
    primaryContainer = DarkSurfaceElevated,
    onPrimaryContainer = VedicGoldLight,
    secondary = KesariyaSaffronLight,
    onSecondary = DarkBackground,
    secondaryContainer = Color(0xFF382215),
    onSecondaryContainer = KesariyaSaffronLight,
    tertiary = VedicGoldGlow,
    onTertiary = DarkBackground,
    tertiaryContainer = Color(0xFF3B2E15),
    onTertiaryContainer = VedicGoldLight,
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkTextSecondary,
    surfaceContainer = DarkSurfaceVariant,
    surfaceContainerHigh = DarkSurfaceElevated,
    outline = DarkCardBorder,
    outlineVariant = DarkCardBorderGold
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalMaroon,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF8E8E8),
    onPrimaryContainer = RoyalMaroonDark,
    secondary = KesariyaSaffron,
    onSecondary = Color.White,
    secondaryContainer = KesariyaSaffronSubtle,
    onSecondaryContainer = KesariyaSaffronDark,
    tertiary = VedicGold,
    onTertiary = Color.White,
    tertiaryContainer = VedicGoldSubtle,
    onTertiaryContainer = VedicGoldDark,
    background = WarmCreamBackground,
    onBackground = TextPrimaryInk,
    surface = CardSurfaceWhite,
    onSurface = TextPrimaryInk,
    surfaceVariant = WarmSandSurface,
    onSurfaceVariant = TextSecondaryMuted,
    surfaceContainer = Color(0xFFFAF3EC),
    surfaceContainerHigh = Color(0xFFF5ECE2),
    outline = CardBorderSubtle,
    outlineVariant = CardBorderGold
)

@Composable
fun ShubhUtsavTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ShubhUtsavTypography,
        shapes = ShubhUtsavShapes,
        content = content
    )
}
