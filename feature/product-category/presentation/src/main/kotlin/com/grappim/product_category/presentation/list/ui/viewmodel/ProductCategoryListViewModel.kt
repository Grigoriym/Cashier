package com.grappim.product_category.presentation.list.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.StateFlow

abstract class ProductCategoryListViewModel : BaseViewModel() {

    abstract val categories: StateFlow<List<ProductCategory>>

    abstract fun goToCategoryDetails(productCategory: ProductCategory)
    abstract fun goToCategoryCreate()
    abstract fun refresh()
}