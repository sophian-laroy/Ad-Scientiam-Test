package com.laroy.adscientiamtest.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.laroy.adscientiamtest.utils.AppDatastore
import kotlinx.coroutines.flow.collect

private val LightColorPalette = lightColors(
    primary = Yellow_AdScientiamTest,
    secondary = Color.Black,
    background = Color.White,
    onPrimary = Color.White,
    onBackground = Color.Black
)

private val DarkColorPalette = darkColors(
    primary = Yellow_AdScientiamTest,
    secondary = Color.White,
    background = Color.DarkGray,
    onPrimary = Color.White,
    onBackground = Color.White
)

@Composable
fun AdScientiamTestTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (appTheme) {
        AppTheme.DARK -> {
            true
        }
        AppTheme.LIGHT -> {
            false
        }
        AppTheme.SYSTEM -> {
            isSystemInDarkTheme()
        }
    }
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}