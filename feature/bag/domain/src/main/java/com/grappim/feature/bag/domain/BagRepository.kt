package com.grappim.feature.bag.domain

import com.grappim.common.lce.Try
import com.grappim.feature.bag.domain.interactor.addProductToBasket.AddProductToBasketParams
import com.grappim.feature.bag.domain.interactor.subtractProductFromBasket.SubtractProductFromBasketParams
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.interactor.addBasketProduct.AddBasketProductParams
import com.grappim.feature.bag.domain.interactor.removeProduct.RemoveProductParams
import kotlinx.coroutines.flow.Flow

interface BagRepository {

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
