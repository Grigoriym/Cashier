package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.sales.AddBasketProduct
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SubtractProductFromBasket
import com.grappim.domain.model.basket.BasketProduct
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    fun getBasketItems(): Flow<List<BasketProduct>>

    fun addBasketProduct(
        params: AddBasketProduct.Params
    ): Flow<Try<BasketProduct>>

    fun clearBasket(): Flow<Try<Unit>>
    fun getBasketProducts(): Flow<Try<List<BasketProduct>>>
    fun subtractProduct(
        params: RemoveProductUseCase.Params
    ): Flow<Try<BasketProduct?>>

    fun addProductToBasket(
        params: AddProductToBasketUseCase.Params
    ): Flow<Try<BasketProduct>>

    fun subtractProduct(
        params: SubtractProductFromBasket.Params
    ): Flow<Try<BasketProduct>>

}