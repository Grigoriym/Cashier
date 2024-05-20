package com.grappim.uikit.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grappim.uikit.R
import com.grappim.uikit.theme.CashierBlue

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
            onClick = backButtonAction
        ) {
            CashierIcon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
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
        backButtonAction = {}
    )
}
