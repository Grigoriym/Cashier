package com.grappim.uikit.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grappim.uikit.R
import com.grappim.uikit.theme.CashierBlue
import java.util.*

@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 4.dp,
    toolbarTitle: String,
    backButtonAction: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        TextButton(
            modifier = Modifier
                .padding(start = 14.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor
            ),
            onClick = backButtonAction,
        ) {
            CashierIcon(
                imageVector = Icons.Filled.ArrowBackIos,
                tint = CashierBlue
            )
        }
        CashierText(
            text = toolbarTitle,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 64.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewBaseTopAppBar() {
    BaseTopAppBar(
        modifier = Modifier,
        toolbarTitle = stringResource(id = R.string.title_menu),
        backButtonAction = {

        }
    )
}