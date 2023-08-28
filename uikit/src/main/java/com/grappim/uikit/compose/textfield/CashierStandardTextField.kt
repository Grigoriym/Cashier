package com.grappim.uikit.compose.textfield

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.theme.CashierBackground
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme

@Composable
fun CashierStandardTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    labelText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            CashierText(
                text = placeholderText,
                color = CashierGray
            )
        },
        readOnly = readOnly,
        enabled = enabled,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = CashierBlue,
            unfocusedIndicatorColor = CashierLightGray,
            backgroundColor = if (isSystemInDarkTheme()) {
                CashierBackground
            } else {
                Color.White
            },
            placeholderColor = CashierGray,
            disabledPlaceholderColor = CashierGray
        ),
        keyboardActions = keyboardActions,
        label = {
            if (labelText != null) {
                CashierText(text = labelText)
            }
        }
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
private fun CashierStandardTextFieldPreview() {
    CashierTheme {
        CashierStandardTextField(
            value = "value",
            onValueChange = {},
            placeholderText = "text"
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CashierStandardTextFieldNightPreview() {
    CashierTheme {
        CashierStandardTextField(
            value = "value",
            onValueChange = {},
            placeholderText = "text"
        )
    }
}
