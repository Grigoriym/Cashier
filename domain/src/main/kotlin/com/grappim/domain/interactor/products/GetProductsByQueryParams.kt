package com.grappim.domain.interactor.products

import com.grappim.product_category.domain.model.ProductCategory

data class GetProductsByQueryParams(
    val category: ProductCategory?,
    val query: String
)
