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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.cashier.R
import com.grappim.cashier.ui.theme.CashierBlue
import com.grappim.cashier.ui.theme.CashierTheme

@Composable
fun BigActionButtonCompose(
    onCreateAcceptanceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onCreateAcceptanceClick,
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CashierBlue
            ),
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    bottom = 16.dp,
                    top = 10.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.action_create_acceptance),
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
            onCreateAcceptanceClick = {}
        )
    }
}