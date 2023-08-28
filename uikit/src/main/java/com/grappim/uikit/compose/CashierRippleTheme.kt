package com.grappim.uikit.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.grappim.uikit.theme.CashierGray

object CashierRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color =
        RippleTheme.defaultRippleColor(
            contentColor = CashierGray,
            lightTheme = !isSystemInDarkTheme()
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            contentColor = Color.Black,
            lightTheme = !isSystemInDarkTheme()
        )

}
