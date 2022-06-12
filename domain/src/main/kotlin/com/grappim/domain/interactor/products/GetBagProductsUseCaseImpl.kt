package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import javax.inject.Inject

class GetBagProductsUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : GetBagProductsUseCase {

    override suspend fun execute(): Try<List<BasketProduct>, Throwable> =
        basketRepository.getBasketProducts()

}