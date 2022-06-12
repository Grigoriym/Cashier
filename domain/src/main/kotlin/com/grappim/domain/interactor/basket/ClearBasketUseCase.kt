package com.grappim.domain.interactor.basket

import com.grappim.common.lce.Try

interface ClearBasketUseCase {

    suspend fun clearBasket(): Try<Unit, Throwable>

}