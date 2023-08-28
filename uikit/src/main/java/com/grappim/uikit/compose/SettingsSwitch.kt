package com.grappim.uikit.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsSwitch(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
    switchValue: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .toggleable(
                    value = switchValue,
                    role = Role.Switch,
                    onValueChange = { onCheckedChange(it) }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsTileIcon(icon = icon)
            SettingsTileTexts(title = title, subtitle = subtitle)
            SettingsTileAction {
                Switch(
                    checked = switchValue,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }
}

@Preview
@Composable
internal fun SettingsSwitchPreview() {
    MaterialTheme {
        SettingsSwitch(
            icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
            title = { Text(text = "Hello") },
            subtitle = { Text(text = "This is a longer text") },
            switchValue = false,
            onCheckedChange = { }
        )
    }
}
