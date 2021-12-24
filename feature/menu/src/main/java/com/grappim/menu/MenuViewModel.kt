package com.grappim.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage,
    private val navigator: Navigator
) : ViewModel() {

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
        navigator.popBackStack()
    }

    private fun showWaybill() {
        navigator.navigateToFlow(NavigationFlow.WaybillFlow)
    }

    private fun showProducts() {
        navigator.navigateToFlow(NavigationFlow.ProductsFlow)
    }

    private fun showSales() {
        navigator.navigateToFlow(NavigationFlow.SalesFlow)
    }
}