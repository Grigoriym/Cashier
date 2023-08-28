package com.grappim.menu.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.menu.helper.MenuItemGenerator
import com.grappim.menu.model.MenuItemPm
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MenuViewModelImpl @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage,
    featureToggleLocalRepository: FeatureToggleLocalRepository
) : MenuViewModel() {

    override val menuItems: StateFlow<List<MenuItemPm>> =
        featureToggleLocalRepository.featureToggleData
            .map {
                menuItemsGenerator.getItems(it)
            }.stateIn(
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
            MenuItemType.SETTINGS -> {
                flowRouter.goToSettings()
            }
        }
    }

    private fun showWaybill() {
        flowRouter.goToWaybill()
    }

    private fun showProducts() {
        flowRouter.goToProducts()
    }

    private fun showSales() {
        flowRouter.goToSales()
    }

    private fun showProductCategories() {
        flowRouter.goToProductCategories()
    }
}
