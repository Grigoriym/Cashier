package com.grappim.domain.model

data class SearchProduct(
    val products: List<Product>,
    val basketProducts: List<BasketProduct>
)
