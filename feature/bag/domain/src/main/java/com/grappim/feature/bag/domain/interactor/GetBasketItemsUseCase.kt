package com.grappim.feature.bag.domain.interactor

import com.grappim.domain.model.BasketProduct
import com.grappim.feature.bag.domain.BagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemsUseCase @Inject constructor(
    private val bagRepository: BagRepository
) {

    fun execute(): Flow<List<BasketProduct>> =
        bagRepository.getBasketItems()
}
