package com.grappim.productcategory.domain.interactor.edit

import com.grappim.productcategory.domain.model.ProductCategory

data class EditProductCategoryParams(
    val newName: String,
    val productCategory: ProductCategory
)
