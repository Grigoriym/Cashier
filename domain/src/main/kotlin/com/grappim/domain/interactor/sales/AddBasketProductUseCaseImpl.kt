package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import javax.inject.Inject

class AddBasketProductUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : AddBasketProductUseCase {

    override suspend fun execute(
        params: AddBasketProductParams
    ): Try<BasketProduct, Throwable> =
        basketRepository.addBasketProduct(params)

}