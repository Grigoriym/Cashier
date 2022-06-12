package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.sales.AddBasketProductParams
import com.grappim.domain.interactor.sales.AddProductToBasketParams
import com.grappim.domain.interactor.sales.RemoveProductParams
import com.grappim.domain.interactor.sales.SubtractProductFromBasketParams
import com.grappim.domain.model.basket.BasketProduct
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    fun getBasketItems(): Flow<List<BasketProduct>>

    suspend fun addBasketProduct(
        params: AddBasketProductParams
    ): Try<BasketProduct, Throwable>

    suspend fun clearBasket(): Try<Unit, Throwable>
    suspend fun getBasketProducts(): Try<List<BasketProduct>, Throwable>
    suspend fun subtractProduct(
        params: RemoveProductParams
    ): Try<BasketProduct?, Throwable>

    suspend fun addProductToBasket(
        params: AddProductToBasketParams
    ): Try<BasketProduct, Throwable>

    suspend fun subtractProduct(
        params: SubtractProductFromBasketParams
    ): Try<BasketProduct, Throwable>

}