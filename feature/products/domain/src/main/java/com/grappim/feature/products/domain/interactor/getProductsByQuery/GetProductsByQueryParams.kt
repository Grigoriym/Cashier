package com.grappim.feature.products.domain.interactor.getProductsByQuery

import com.grappim.product_category.domain.model.ProductCategory

data class GetProductsByQueryParams(
    val category: ProductCategory?,
    val query: String
)
