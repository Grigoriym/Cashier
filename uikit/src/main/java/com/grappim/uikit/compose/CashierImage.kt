package com.grappim.uikit.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CashierImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}

@Composable
fun CashierImage(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String = "",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}