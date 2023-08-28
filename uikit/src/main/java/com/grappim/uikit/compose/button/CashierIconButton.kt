package com.grappim.uikit.compose.button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.vector.ImageVector
import com.grappim.uikit.compose.CashierRippleTheme
import com.grappim.uikit.theme.CashierGray

@Composable
fun CashierIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CashierRippleTheme) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = CashierGray
            )
        }
    }
}
