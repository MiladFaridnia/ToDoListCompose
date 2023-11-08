package com.faridnia.todolistcompose.presentaion.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF655DBB),
    secondary = Color(0xFF7882A4),
    tertiary = Color(0xFF4477CE),
//    surface = Color(0xFF35155D),
//    surface = Color(0xFF7882A4),
//    surface = Color(0xFF354259),
    surface = Color(0xFF65647C),
    onSurface = Color(0xFFECF2FF),
    onSurfaceVariant = Color(0xFFECF2FF),
    background = Color(0xFF4C4C6D),
    onBackground = Color(0xFFECF2FF),
    onPrimary = Color(0xFFECF2FF),
    error = Color(0xFF7A3E3E),
    onError = Color(0xFFECF2FF),
    errorContainer = Color(0xFF7A3E3E),
    onErrorContainer = Color(0xFFECF2FF),
    outline = Color(0xFF512B81),
    outlineVariant = Color(0xFF4477CE)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF439A97),
    secondary = Color(0xFFBCEAD5),
    tertiary = Color(0xFF9ED5C5),
    surface = Color(0xFFECF9FF),
    onSurfaceVariant = Color(0xFF181D31),
    onSurface = Color(0xFF354259),
    background = Color(0xFFDEF5E5),
    onBackground = Color(0xFF354259),
    onPrimary = Color(0xFFECF9FF),
    error = Color(0xFFDA7F8F),
    onError = Color(0xFFFAF3F3),
    errorContainer = Color(0xFFDA7F8F),
    onErrorContainer = Color(0xFFFAF3F3),
    outline = Color(0xFF354259),
    outlineVariant = Color(0xFF354259)
)

@Composable
fun ToDoListComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            DarkColorScheme
        else
            LightColorScheme,
        typography = Typography,
        content = content
    )
}