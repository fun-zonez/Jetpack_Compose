package com.mystrox.arc.ui.theme

import android.R.attr.enabled
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF8CB69),
//    secondary = Color(0xFFF8CB69),
//    tertiary = Color(0xFFF8CB69),
    background = Color(0xFFA4193D),
    surface = Color(0xFFF8CB69),
    onPrimary = Color(0xFF7A0000),
//    onSecondary = Color(0xFF7A0000),
    onTertiary = Color(0xFFFFEA00),
//    onBackground = Color(0xFFFFA000),
//    onSurface = Color(0xFFF57C00)


)

private val LightColorScheme = lightColorScheme(

    primary = Color(0xFFA4193D),
//    secondary = Color(0xFFFFEA00),
//    tertiary = Color(0xFFF8CB69),
    background = Color(0xFFF8CB69),
    surface = Color(0xFFA4193D),
    onPrimary = Color(0xFFF8CB69),
//    onSecondary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF7A0000),
////    onBackground = Color(0xFF4300FF),
////    onSurface = Color(0xFF1976D2)

)

@Composable
fun ArcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val LightMyColors = lightColorScheme(
    background = Color(0xFFE2E2E2),
    primary = Color(0xFF000000),
    secondary = Color(0xFFEE3C4A),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF)
)

val DarkMyColors = darkColorScheme(
    background = Color(0xFF333333),
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFFEE3C4A),
    onBackground = Color(0xFF5C5C5C),
    onSurface = Color(0xFFFFFFFF)
)
