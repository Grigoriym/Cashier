package com.grappim.feature.settings.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.feature.settings.R
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun SettingsScreen(
    info: String
) {
    Scaffold {
        SettingsScreenContent(info)
    }
}

@Composable
private fun SettingsScreenContent(
    info: String
) {
    val infiniteTransition = rememberInfiniteTransition()
    val logoSize by infiniteTransition.animateFloat(
        initialValue = 50f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(800,100, FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            SettingsMenuItem(title = {
                Text(text = stringResource(id = R.string.title_about))
            }, subtitle = {
                Text(text = info)
            }) {

            }
        }
        item {
            Spacer(modifier = Modifier.size(20.dp))
        }
//        item {
//            Image(
//                modifier = Modifier
//                    .size(logoSize.dp),
//                painter = painterResource(id = R.drawable.ic_logo),
//                contentDescription = "Logo"
//            )
//        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun SettingsScreenContentPreview() {
    CashierTheme {
        SettingsScreenContent(
            info = "test"
        )
    }
}