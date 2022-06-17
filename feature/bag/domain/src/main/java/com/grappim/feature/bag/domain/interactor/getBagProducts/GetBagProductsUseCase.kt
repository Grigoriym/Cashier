package com.grappim.feature.bag.domain.interactor.getBagProducts

import com.grappim.common.lce.Try
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class GetBagProductsUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    suspend fun execute(): Try<List<BasketProduct>, Throwable> =
        bagRepository.getBasketProducts()

}