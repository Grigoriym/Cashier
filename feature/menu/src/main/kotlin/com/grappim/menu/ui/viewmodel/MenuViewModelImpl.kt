package com.grappim.menu.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.domain.storage.GeneralStorage
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.menu.helper.MenuItemGenerator
import com.grappim.menu.model.MenuItemPm
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MenuViewModelImpl @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage,
    private val menuScreenNavigator: MenuScreenNavigator
) : MenuViewModel() {

    override val menuItems: StateFlow<List<MenuItemPm>> =
        menuItemsGenerator
            .getItems()
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    override val cashierName: StateFlow<String> = flow {
        emit(generalStorage.cashBoxName)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = ""
    )

    override fun onItemClick(menuItemPm: MenuItemPm) {
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
            MenuItemType.PRODUCT_CATEGORY -> {
                showProductCategories()
            }
        }
    }

    override fun onBackPressed() {
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

    private fun showProductCategories() {
        menuScreenNavigator.goToProductCategories()
    }
}