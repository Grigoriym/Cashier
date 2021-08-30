package com.grappim.cashier.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.cashier.ui.theme.CashierBlue
import com.grappim.cashier.ui.theme.CashierBlueDisabled
import com.grappim.cashier.ui.theme.CashierGray
import com.grappim.cashier.ui.theme.CashierTheme

@Composable
fun BigActionButtonCompose(
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    val backgroundColor = if (isEnabled) {
        CashierBlue
    } else {
        CashierBlueDisabled
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor
            ),
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    bottom = 16.dp,
                    top = 10.dp
                ),
            enabled = isEnabled
        ) {
            Text(
                text = buttonText,
                color = Color.White,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(
                        top = 13.dp,
                        bottom = 13.dp
                    )
            )
        }
    }
}

@Composable
@Preview
private fun BottomButtonSegmentPreview() {
    CashierTheme {
        BigActionButtonCompose(
            buttonText = "Test Click",
            onButtonClick = {}
        )
    }
}