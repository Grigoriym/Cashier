package com.grappim.domain.model.basket

import com.grappim.domain.model.product.Product

data class SearchProduct(
    val products: List<Product>,
    val basketProducts: List<BasketProduct>
)
