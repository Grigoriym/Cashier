package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct

interface AddProductToBasketUseCase {
    suspend fun execute(
        params: AddProductToBasketParams
    ): Try<BasketProduct, Throwable>
}