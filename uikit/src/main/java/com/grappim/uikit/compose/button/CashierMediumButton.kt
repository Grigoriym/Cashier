package com.grappim.uikit.compose.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.grappim.uikit.compose.CashierText

@Composable
fun CashierMediumButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    backgroundColor: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary
    ),
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = backgroundColor,
        shape = RoundedCornerShape(25.dp),
        enabled = enabled
    ) {
        CashierText(
            text = text,
            color = textColor,
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                )
        )
    }
}
