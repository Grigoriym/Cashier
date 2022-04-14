package com.grappim.uikit.compose.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CashierImageButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contextDescription: String = "",
    onIconClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .padding(8.dp),
        onClick = onIconClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contextDescription
        )
    }
}