package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct

interface SubtractProductFromBasketUseCase {
    suspend fun execute(
        params: SubtractProductFromBasketParams
    ): Try<BasketProduct, Throwable>
}