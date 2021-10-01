package com.grappim.uikit.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.uikit.R
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun StandardFilledButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    enabled: Boolean = true,
    @DrawableRes iconDrawable: Int,
    backgroundColor: Color = CashierBlue
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = CashierBlue
        ),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        enabled = enabled
    ) {
        Image(
            painter = painterResource(id = iconDrawable),
            contentDescription = ""
        )
    }
}

@Composable
@Preview
private fun StandardFilledButtonPreview() {
    CashierTheme {
        StandardFilledButton(
            onButtonClick = {
            },
            iconDrawable = R.drawable.ic_search
        )
    }
}