package com.grappim.uikit.compose.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.uikit.R
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.theme.CashierGreySuit
import com.grappim.uikit.theme.CashierTheme

@Composable
fun CashierOutlinedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(10.dp),
        leadingIcon = {
            CashierIcon(
                imageVector = Icons.Filled.Search,
                tint = CashierGreySuit
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            CashierText(
                text = stringResource(
                    id = R.string.action_search
                ),
                color = CashierGreySuit
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeAction()
                keyboardController?.hide()
            }
        )
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun OutlinedTextFieldComposePreview() {
    CashierTheme {
        CashierOutlinedTextField(
            text = "text",
            onTextChange = {}
        )
    }
}