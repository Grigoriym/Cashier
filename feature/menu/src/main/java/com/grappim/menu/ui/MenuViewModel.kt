package com.grappim.menu.ui

import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.core.BaseViewModel
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.domain.storage.GeneralStorage
import com.grappim.menu.helper.MenuItemGenerator
import com.grappim.menu.model.MenuItemPm
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import com.grappim.navigation.directions.menu.MenuScreenNavigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage,
    private val menuScreenNavigator: MenuScreenNavigator
) : BaseViewModel() {

    val menuItems: StateFlow<List<MenuItemPm>> =
        menuItemsGenerator
            .getItems()
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = listOf()
            )

    val cashierName: StateFlow<String> = flow {
        emit(generalStorage.cashBoxName)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = ""
    )

    fun onItemClick(menuItemPm: MenuItemPm) {
        when (menuItemPm.type) {
            MenuItemType.ACCEPTANCE -> {
                showWaybill()
            }
            MenuItemType.PRODUCTS -> {
                showProducts()
            }
            MenuItemType.SALES -> {
                showSales()
            }
        }
    }

    fun onBackPressed() {
        menuScreenNavigator.goBack()
    }

    private fun showWaybill() {
        menuScreenNavigator.goToWaybill()
    }

    private fun showProducts() {
        menuScreenNavigator.goToProducts()
    }

    private fun showSales() {
        menuScreenNavigator.goToSales()
    }
}