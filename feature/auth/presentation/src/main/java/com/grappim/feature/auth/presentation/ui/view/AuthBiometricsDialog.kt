package com.grappim.feature.auth.presentation.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.feature.auth.presentation.R

@Composable
internal fun AuthBiometricsDialog(
    open: Boolean,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
    val openDialog = remember {
        mutableStateOf(open)
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.biometrics_dialog_set_touch_id))
            },
            text = {
                Text(text = stringResource(id = R.string.biometrics_dialog_description))
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = {
                            openDialog.value = false
                            onNegativeClick()
                        }) {
                        Text(text = stringResource(id = R.string.action_no))
                    }
                    Button(
                        onClick = {
                            openDialog.value = false
                            onPositiveClick()
                        }) {
                        Text(text = stringResource(id = R.string.action_yes))
                    }
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthBiometricsDialogPreview() {
    AuthBiometricsDialog(
        open = true,
        onPositiveClick = {},
        onNegativeClick = {}
    )
}