package com.grappim.feature.bag.domain.interactor.addBasketProduct

import com.grappim.common.lce.Try
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class AddBasketProductUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    suspend fun execute(
        params: AddBasketProductParams
    ): Try<BasketProduct, Throwable> =
        bagRepository.addBasketProduct(params)

}
