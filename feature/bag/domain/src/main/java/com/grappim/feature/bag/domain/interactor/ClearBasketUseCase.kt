package com.grappim.feature.bag.domain.interactor

import com.grappim.cashier.common.lce.Try
import com.grappim.feature.bag.domain.BagRepository
import javax.inject.Inject

class ClearBasketUseCase @Inject constructor(private val bagRepository: BagRepository) {

    suspend fun clearBasket(): Try<Unit, Throwable> = bagRepository.clearBasket()
}
