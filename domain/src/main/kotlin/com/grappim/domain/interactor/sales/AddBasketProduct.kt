package com.grappim.domain.interactor.sales

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddBasketProduct @Inject constructor(
    private val basketRepository: BasketRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<AddBasketProduct.Params, BasketProduct>(ioDispatcher) {

    data class Params(
        val product: BasketProduct
    )

    override fun execute(params: Params): Flow<Try<BasketProduct>> =
        basketRepository.addBasketProduct(params)

}