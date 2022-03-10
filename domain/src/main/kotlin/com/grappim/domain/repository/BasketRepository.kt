package com.grappim.domain.repository

import com.grappim.domain.model.product.Product

interface BasketRepository {

    fun addProduct(product: Product)
    fun subtractProduct(product: Product)
    fun removeProduct(product: Product)
    fun clearBasket()

}