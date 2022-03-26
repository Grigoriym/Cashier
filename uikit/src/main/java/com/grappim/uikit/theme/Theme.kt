package com.grappim.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
    primary = CashierBlue,
    secondary = CashierBlue
)

private val DarkThemeColors = darkColors(
    primary = CashierBlue,
    secondary = CashierBlue
)

@Composable
fun CashierTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
//        colors = if (darkTheme) {
//            DarkThemeColors
//        } else {
//            LightThemeColors
//        },
        colors = LightThemeColors,
        shapes = CashierShapes,
        content = content
    )
}