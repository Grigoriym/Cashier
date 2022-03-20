package com.grappim.menu.ui.viewmodel

import com.grappim.core.base.BaseViewModel2
import com.grappim.menu.model.MenuItemPm
import kotlinx.coroutines.flow.StateFlow

abstract class MenuViewModel : BaseViewModel2() {

    abstract val menuItems: StateFlow<List<MenuItemPm>>
    abstract val cashierName: StateFlow<String>
    abstract fun onItemClick(menuItemPm: MenuItemPm)

}