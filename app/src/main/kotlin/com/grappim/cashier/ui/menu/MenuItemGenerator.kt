package com.grappim.cashier.ui.menu

import com.grappim.cashier.R
import com.grappim.domain.model.menu.MenuItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuItemGenerator @Inject constructor(

) {

    fun getItems(): Flow<List<MenuItemPm>> = flow {
        emit(
            listOf(
                MenuItemPm(
                    type = MenuItemType.SALES,
                    text = R.string.title_sales,
                    image = R.drawable.ic_cash_register
                ),
                MenuItemPm(
                    type = MenuItemType.PRODUCTS,
                    text = R.string.title_products,
                    image = R.drawable.ic_store
                ),
                MenuItemPm(
                    type = MenuItemType.ACCEPTANCE,
                    text = R.string.title_acceptance,
                    image = R.drawable.ic_store_acceptance
                )
            )
        )
    }
}