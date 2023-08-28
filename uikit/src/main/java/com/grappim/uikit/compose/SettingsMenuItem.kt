package com.grappim.uikit.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsMenuItem(
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    Surface {
        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SettingsTileIcon(icon = icon)
                SettingsTileTexts(title = title, subtitle = subtitle)
            }
            if (action != null) {
                Divider(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .height(56.dp)
                        .width(1.dp),
                )
                SettingsTileAction {
                    action.invoke()
                }
            }
        }
    }
}

@Composable
internal fun SettingsTileIcon(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier.size(64.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                icon()
            }
        }
    }
}

@Composable
internal fun RowScope.SettingsTileTexts(
    title: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)?,
) {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.Center,
    ) {
        SettingsTileTitle(title)
        if (subtitle != null) {
            Spacer(modifier = Modifier.size(2.dp))
            SettingsTileSubtitle(subtitle)
        }
    }
}

@Composable
internal fun SettingsTileTitle(title: @Composable () -> Unit) {
    ProvideTextStyle(value = MaterialTheme.typography.subtitle1) {
        title()
    }
}

@Composable
internal fun SettingsTileSubtitle(subtitle: @Composable () -> Unit) {
    ProvideTextStyle(value = MaterialTheme.typography.caption) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium,
            content = subtitle
        )
    }
}

@Composable
internal fun SettingsTileAction(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.size(64.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
