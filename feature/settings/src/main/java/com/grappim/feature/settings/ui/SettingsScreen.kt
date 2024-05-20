package com.grappim.feature.settings.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.SettingsMenuItem
import com.grappim.uikit.compose.SettingsSwitch
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun SettingsScreen(
    info: String,
    onGithubSrcClick: () -> Unit,
    useBiometrics: Boolean,
    onUseBiometricsChecked: (Boolean) -> Unit,
    onBackButtonPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_settings)
            ) {
                onBackButtonPressed()
            }
        }
    ) {
        SettingsScreenContent(
            modifier = Modifier.padding(it),
            info = info,
            onGithubSrcClick = onGithubSrcClick,
            onUseBiometricsChecked = onUseBiometricsChecked,
            useBiometrics = useBiometrics
        )
    }
}

@Composable
private fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    info: String,
    onGithubSrcClick: () -> Unit,
    useBiometrics: Boolean,
    onUseBiometricsChecked: (Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SettingsMenuItem(title = {
                Text(text = stringResource(id = R.string.title_about))
            }, subtitle = {
                Text(text = info)
            }) {}
        }
        item {
            SettingsMenuItem(
                title = {
                    Text(text = stringResource(id = R.string.title_github_src))
                },
                onClick = onGithubSrcClick
            )
        }
        item {
            SettingsSwitch(
                title = {
                    Text(text = stringResource(id = R.string.title_use_biometrics))
                },
                onCheckedChange = onUseBiometricsChecked,
                switchValue = useBiometrics
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun SettingsScreenContentPreview() {
    CashierTheme {
        SettingsScreenContent(
            info = "test",
            onGithubSrcClick = {},
            useBiometrics = true,
            onUseBiometricsChecked = {}
        )
    }
}
