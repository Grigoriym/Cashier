package com.grappim.domain.interactor.basket

import com.grappim.common.lce.Try
import com.grappim.domain.repository.BasketRepository
import javax.inject.Inject

class ClearBasketUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : ClearBasketUseCase {

    override suspend fun clearBasket(): Try<Unit, Throwable> =
        basketRepository.clearBasket()

}