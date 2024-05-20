package com.grappim.cashier.feature.productcategory.presentation.list.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import kotlinx.coroutines.flow.StateFlow

abstract class ProductCategoryListViewModel : BaseViewModel() {

    abstract val categories: StateFlow<List<ProductCategory>>

    abstract fun goToCategoryDetails(productCategory: ProductCategory)
    abstract fun goToCategoryCreate()
    abstract fun refresh()
}
