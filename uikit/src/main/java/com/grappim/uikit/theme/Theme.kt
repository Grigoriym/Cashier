package com.grappim.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = CashierBlue,
    secondary = CashierBlue,
    surface = Color.White,
    onSurface = Color.White,
    onBackground = Color.Black
)

private val DarkThemeColors = darkColors(
    primary = CashierBlue,
    secondary = CashierBlue,
    surface = Color.Black,
    onSurface = CashierGray,
    onBackground = Color.White,
    background = CashierBackground
)

@Composable
fun CashierTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
//    val systemUiController = rememberSystemUiController()
//    val useDarkIcons = MaterialTheme.colors.isLight
//
//    SideEffect {
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons
//        )
//    }

    MaterialTheme(
        colors = if (darkTheme) {
            DarkThemeColors
        } else {
            LightThemeColors
        },
        shapes = CashierShapes,
        content = content
    )
}
