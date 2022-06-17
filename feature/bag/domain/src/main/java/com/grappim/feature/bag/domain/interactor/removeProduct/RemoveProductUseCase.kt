package com.grappim.feature.bag.domain.interactor.removeProduct

import com.grappim.common.lce.Try
import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class RemoveProductUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    suspend fun execute(params: RemoveProductParams): Try<BasketProduct?, Throwable> =
        bagRepository.subtractProduct(params)
}