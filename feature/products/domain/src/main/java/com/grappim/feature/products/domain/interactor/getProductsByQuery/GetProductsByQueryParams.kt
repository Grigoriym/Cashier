package com.grappim.feature.products.domain.interactor.getProductsByQuery

import com.grappim.productcategory.domain.model.ProductCategory

data class GetProductsByQueryParams(
    val category: ProductCategory?,
    val query: String
)
