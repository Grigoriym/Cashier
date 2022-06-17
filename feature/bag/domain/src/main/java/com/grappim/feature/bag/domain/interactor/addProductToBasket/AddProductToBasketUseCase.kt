package com.grappim.feature.bag.domain.interactor.addProductToBasket

import com.grappim.common.lce.Try
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class AddProductToBasketUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    suspend fun execute(
        params: AddProductToBasketParams
    ): Try<BasketProduct, Throwable> =
        bagRepository.addProductToBasket(params)

}