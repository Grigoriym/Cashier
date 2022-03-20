package com.grappim.domain.interactor.basket

import com.grappim.common.asynchronous.SimpleFlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val basketRepository: BasketRepository
) : SimpleFlowUseCase<NoParams, List<BasketProduct>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<List<BasketProduct>> =
        basketRepository.getBasketItems()

}