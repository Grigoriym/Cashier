package com.grappim.cashier.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.domain.menu.GetMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    getMenuItemsUseCase: GetMenuItemsUseCase,
    generalStorage: GeneralStorage
) : ViewModel() {

    val menuItems: StateFlow<List<MenuItem>> = flow {
        emit(getMenuItemsUseCase.getMenuItems())
    }.stateIn(
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