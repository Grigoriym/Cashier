package com.grappim.cashier.compose

import androidx.compose.foundation.layout.*
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
import com.grappim.cashier.ui.theme.CashierTheme

@Composable
fun LoaderDialogCompose(
    show: Boolean,
    onClose: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentSize(),
                shape = RoundedCornerShape(16.dp),
                color = Color.LightGray
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
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