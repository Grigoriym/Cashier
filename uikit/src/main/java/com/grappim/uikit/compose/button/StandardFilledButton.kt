package com.grappim.uikit.compose.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun StandardFilledButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    enabled: Boolean = true,
    imageVector: ImageVector,
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
            CashierIcon(
                imageVector = imageVector,
                tint = iconTint
            )
        } else {
            CashierIcon(imageVector = imageVector)
        }
    }
}

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
            CashierIcon(
                painter = painterResource(id = iconDrawable),
                tint = iconTint
            )
        } else {
            CashierIcon(
                painter = painterResource(id = iconDrawable)
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
            imageVector = Icons.Filled.Search,
            text = "4"
        )
    }
}
