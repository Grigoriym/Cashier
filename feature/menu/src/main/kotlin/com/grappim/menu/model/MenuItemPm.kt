package com.grappim.menu.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.grappim.domain.model.menu.MenuItemType

data class MenuItemPm(
    val type: MenuItemType,
    @StringRes val text: Int,
    @DrawableRes val image: Int? = null,
    val imageVector: ImageVector? = null
)
