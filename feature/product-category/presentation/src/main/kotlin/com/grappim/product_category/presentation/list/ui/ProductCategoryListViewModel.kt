package com.grappim.product_category.presentation.list.ui

import com.grappim.core.BaseViewModel
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.StateFlow

abstract class ProductCategoryListViewModel : BaseViewModel() {

    abstract val categories: StateFlow<List<ProductCategory>>

    abstract fun goToCategoryDetails(productCategory: ProductCategory)
    abstract fun goToCategoryCreate()

    abstract fun onBackPressed()
}