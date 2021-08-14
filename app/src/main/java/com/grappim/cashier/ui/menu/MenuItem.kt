package com.grappim.cashier.ui.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    val type: MenuItemType,
    @StringRes val text: Int,
    @DrawableRes val image: Int
)