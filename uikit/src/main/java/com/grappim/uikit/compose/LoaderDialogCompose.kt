package com.grappim.uikit.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.grappim.uikit.theme.CashierTheme

@Suppress("MagicNumber")
@Composable
fun LoaderDialogCompose(show: Boolean, onClose: (() -> Unit)? = null) {
    if (show) {
        Dialog(
            onDismissRequest = {
                onClose?.invoke()
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(16.dp),
                color = Color.Transparent
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(0.4f),
                        strokeWidth = 8.dp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoaderDialogComposePreview() {
    CashierTheme {
        LoaderDialogCompose(
            show = true,
            onClose = {}
        )
    }
}
