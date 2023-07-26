package com.grappim.menu.helper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import com.grappim.domain.model.FeatureToggle
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.uikit.R
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
                imageVector = Icons.Filled.AddBusiness
            )
        )
        if (featureToggle.isWaybillEnabled) {
            result.add(
                MenuItemPm(
                    type = MenuItemType.ACCEPTANCE,
                    text = R.string.title_acceptance,
                    imageVector = Icons.Filled.Work
                )
            )
        }
        result.add(
            MenuItemPm(
                type = MenuItemType.PRODUCT_CATEGORY,
                text = R.string.title_product_categories,
                imageVector = Icons.Filled.Category
            )
        )
        result.add(
            MenuItemPm(
                type = MenuItemType.SETTINGS,
                text = R.string.title_settings,
                imageVector = Icons.Filled.Settings
            )
        )
        return result.toList()
    }
}