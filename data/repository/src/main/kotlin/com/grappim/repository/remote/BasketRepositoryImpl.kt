package com.grappim.repository.remote

import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.BasketRepository
import com.grappim.network.api.BasketApi
import com.grappim.network.di.api.QualifierBasketApi
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    @QualifierBasketApi private val basketApi: BasketApi
) : BasketRepository {
    override fun addProduct(product: Product) {
    }

    override fun subtractProduct(product: Product) {
    }

    override fun removeProduct(product: Product) {
    }

    override fun clearBasket() {
    }
}