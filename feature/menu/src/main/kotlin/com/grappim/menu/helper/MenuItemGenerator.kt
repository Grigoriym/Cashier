package com.grappim.menu.helper

import com.grappim.domain.model.FeatureToggle
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.menu.R
import com.grappim.menu.model.MenuItemPm
import javax.inject.Inject

class MenuItemGenerator @Inject constructor(

) {

    fun getItems(featureToggle: FeatureToggle): List<MenuItemPm> {
        val result = mutableListOf<MenuItemPm>()
        if (featureToggle.isSalesEnabled) {
            result.add(
                MenuItemPm(
                    type = MenuItemType.SALES,
                    text = R.string.title_sales,
                    image = R.drawable.ic_cash_register
                )
            )
        }
        result.add(
            MenuItemPm(
                type = MenuItemType.PRODUCTS,
                text = R.string.title_products,
                image = R.drawable.ic_store
            )
        )
        if (featureToggle.isWaybillEnabled) {
            result.add(
                MenuItemPm(
                    type = MenuItemType.ACCEPTANCE,
                    text = R.string.title_acceptance,
                    image = R.drawable.ic_store_acceptance
                )
            )
        }
        result.add(
            MenuItemPm(
                type = MenuItemType.PRODUCT_CATEGORY,
                text = R.string.title_product_categories,
                image = R.drawable.ic_store_acceptance
            )
        )
        return result.toList()
    }
}