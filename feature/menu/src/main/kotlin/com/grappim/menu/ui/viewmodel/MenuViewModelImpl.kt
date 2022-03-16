package com.grappim.menu.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.navigation.AppRouter
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.domain.storage.GeneralStorage
import com.grappim.menu.helper.MenuItemGenerator
import com.grappim.menu.model.MenuItemPm
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MenuViewModelImpl @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage,
    private val appRouter: AppRouter
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

    override fun onBackPressed2() {
        appRouter.goBack()
    }

    private fun showWaybill() {
        appRouter.goToWaybill()
    }

    private fun showProducts() {
        appRouter.goToProducts()
    }

    private fun showSales() {
        appRouter.goToSales()
    }

    private fun showProductCategories() {
        appRouter.goToProductCategories()
    }
}