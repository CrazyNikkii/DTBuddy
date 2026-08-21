package com.dtbuddy.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Charcoal = Color(0xFF10151B)
private val BlueGreySurface = Color(0xFF1B2530)
private val RaisedBlueGreySurface = Color(0xFF263342)
private val HighContrastText = Color(0xFFF4F1EB)
private val MutedText = Color(0xFFC4C8CD)
private val FieryOrange = Color(0xFFFF7A00)
private val WarmGold = Color(0xFFF2C66D)

private val DTBuddyDarkColorScheme = darkColorScheme(
    primary = FieryOrange,
    onPrimary = Charcoal,
    primaryContainer = Color(0xFF5A2D00),
    onPrimaryContainer = Color(0xFFFFDCC2),
    secondary = WarmGold,
    onSecondary = Charcoal,
    secondaryContainer = Color(0xFF4A3A16),
    onSecondaryContainer = Color(0xFFFFE9B1),
    background = Charcoal,
    onBackground = HighContrastText,
    surface = BlueGreySurface,
    onSurface = HighContrastText,
    surfaceVariant = RaisedBlueGreySurface,
    onSurfaceVariant = MutedText,
    outline = Color(0xFF84909C),
)

@Composable
fun DTBuddyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DTBuddyDarkColorScheme,
        content = content,
    )
}
