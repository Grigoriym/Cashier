package com.grappim.cashier.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grappim.cashier.R
import com.grappim.cashier.ui.theme.CashierBlue
import java.util.*

@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 4.dp,

    toolbarTitle: String,
    backButtonTitle: String,
    backButtonAction: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        contentColor = contentColor,
        backgroundColor = Color.White,
        elevation = elevation
    ) {
        TextButton(
            modifier = Modifier
                .padding(start = 14.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = colorResource(
                    id = R.color.cashier_white
                )
            ),
            onClick = backButtonAction,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_ios_24),
                tint = CashierBlue,
                contentDescription = "Back button"
            )
            Text(
                text = backButtonTitle.toUpperCase(
                    Locale.current
                ),
                color = CashierBlue
            )
        }
        Text(
            text = toolbarTitle,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 64.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview
@Composable
private fun PreviewBaseTopAppBar() {
    BaseTopAppBar(
        modifier = Modifier,
        toolbarTitle = stringResource(id = R.string.title_menu),
        backButtonTitle = stringResource(id = R.string.action_back),
        backButtonAction = {

        }
    )
}