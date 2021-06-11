package com.grappim.cashier.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.domain.menu.GetMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    getMenuItemsUseCase: GetMenuItemsUseCase,
) : ViewModel() {

    private val _menuItems: Flow<List<MenuItem>> = getMenuItemsUseCase.getMenuItems()
    val menuItems: LiveData<List<MenuItem>> = _menuItems
        .asLiveData(viewModelScope.coroutineContext)
}