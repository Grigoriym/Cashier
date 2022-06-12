package com.grappim.domain.interactor.sales

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import javax.inject.Inject

class AddProductToBasketUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : AddProductToBasketUseCase {

    override suspend fun execute(
        params: AddProductToBasketParams
    ): Try<BasketProduct, Throwable> =
        basketRepository.addProductToBasket(params)

}