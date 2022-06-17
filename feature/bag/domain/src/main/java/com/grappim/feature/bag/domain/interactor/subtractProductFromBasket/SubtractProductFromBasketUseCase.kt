package com.grappim.feature.bag.domain.interactor.subtractProductFromBasket

import com.grappim.common.lce.Try
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class SubtractProductFromBasketUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    suspend fun execute(
        params: SubtractProductFromBasketParams
    ): Try<BasketProduct, Throwable> =
        bagRepository.subtractProduct(params)
}