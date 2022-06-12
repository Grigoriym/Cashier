package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct

interface RemoveProductUseCase {
    suspend fun execute(
        params: RemoveProductParams
    ): Try<BasketProduct?, Throwable>
}