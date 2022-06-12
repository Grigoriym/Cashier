package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct

interface AddBasketProductUseCase {

    suspend fun execute(
        params: AddBasketProductParams
    ): Try<BasketProduct, Throwable>
}