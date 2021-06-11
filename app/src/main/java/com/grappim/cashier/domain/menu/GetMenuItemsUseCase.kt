package com.grappim.cashier.domain.menu

import com.grappim.cashier.domain.repository.GeneralRepository
import com.grappim.cashier.ui.menu.MenuItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuItemsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    fun getMenuItems(): Flow<List<MenuItem>> = generalRepository.getMenuItems()
}