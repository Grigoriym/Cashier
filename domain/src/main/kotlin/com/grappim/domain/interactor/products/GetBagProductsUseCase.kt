package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct

interface GetBagProductsUseCase {
    suspend fun execute(): Try<List<BasketProduct>, Throwable>
}