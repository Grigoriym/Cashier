package com.grappim.product_category.domain.interactor

import com.grappim.product_category.domain.model.ProductCategory

data class EditProductCategoryParams(
    val newName: String,
    val productCategory: ProductCategory
)
