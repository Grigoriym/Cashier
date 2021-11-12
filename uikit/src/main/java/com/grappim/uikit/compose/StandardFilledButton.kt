package com.grappim.uikit.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.R
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun StandardFilledButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    enabled: Boolean = true,
    @DrawableRes iconDrawable: Int,
    backgroundColor: Color = CashierBlue,
    text: String? = null,
    iconTint: Color? = null
) {
    val borderStroke = if (backgroundColor == CashierBlue) {
        null
    } else {
        BorderStroke(
            width = 1.dp,
            color = CashierBlue
        )
    }
    Button(
        onClick = onButtonClick,
        modifier = modifier,
        border = borderStroke,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        enabled = enabled
    ) {
        if (text != null) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(
                        end = 8.dp
                    )
            )
        }
        if (iconTint != null) {
            Icon(
                painter = painterResource(id = iconDrawable),
                contentDescription = "",
                tint = iconTint
            )
        } else {
            Icon(
                painter = painterResource(id = iconDrawable),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview
private fun StandardFilledButtonPreview() {
    CashierTheme {
        StandardFilledButton(
            modifier = Modifier
                .fillMaxWidth(),
            onButtonClick = {},
            iconDrawable = R.drawable.ic_search,
            text = "4"
        )
    }
}