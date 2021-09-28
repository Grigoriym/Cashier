package com.grappim.cashier.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.cashier.model.menu.MenuItemPm
import com.grappim.domain.storage.GeneralStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    menuItemsGenerator: MenuItemGenerator,
    generalStorage: GeneralStorage
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
        emit(generalStorage.getCashierName())
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = ""
    )
}