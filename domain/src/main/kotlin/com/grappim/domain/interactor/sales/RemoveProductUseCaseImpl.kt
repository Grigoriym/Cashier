package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import javax.inject.Inject

class RemoveProductUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : RemoveProductUseCase {

    override suspend fun execute(params: RemoveProductParams): Try<BasketProduct?, Throwable> =
        basketRepository.subtractProduct(params)
}