package com.grappim.cashier.feature.productcategory.domain.interactor.edit

import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory

data class EditProductCategoryParams(
    val newName: String,
    val productCategory: ProductCategory
)
