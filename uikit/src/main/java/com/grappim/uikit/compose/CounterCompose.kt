package com.grappim.uikit.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme

@Composable
fun CounterComposable(
    modifier: Modifier = Modifier,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    text: String
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onMinusClick,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            modifier = Modifier
                .width(60.dp)
                .height(32.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = CashierLightGray
            )
        ) {
            CashierIcon(
                imageVector = Icons.Filled.Remove,
                tint = CashierBlue
            )
        }

        Box(
            modifier = Modifier
                .height(32.dp)
                .background(
                    color = Color.White
                )
                .drawBehind {
                    val strokeWidth = 3.0f
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = CashierLightGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )

                    val yTop = 0f + strokeWidth / 2
                    drawLine(
                        color = CashierLightGray,
                        start = Offset(0f, yTop),
                        end = Offset(size.width, yTop),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            CashierText(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(70.dp),
                color = CashierGray,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = onPlusClick,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp
            ),
            modifier = Modifier
                .width(60.dp)
                .height(32.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = CashierLightGray
            )
        ) {
            CashierIcon(
                imageVector = Icons.Filled.Add
            )
        }
    }
}

@Composable
@Preview
private fun CounterPreview() {
    CashierTheme {
        CounterComposable(
            onMinusClick = { },
            onPlusClick = { },
            text = "1 pc"
        )
    }
}