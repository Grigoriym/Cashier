package com.grappim.product_category.presentation.create_edit.ui

import com.grappim.core.BaseViewModel
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import javax.inject.Inject

class CreateEditProductCategoryViewModel @Inject constructor(
    private val productCategoryScreenNavigator: ProductCategoryScreenNavigator
) : BaseViewModel() {

    fun onBackPressed() {
        productCategoryScreenNavigator.goBack()
    }
}