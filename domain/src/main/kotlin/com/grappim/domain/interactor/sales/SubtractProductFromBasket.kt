package com.grappim.domain.interactor.sales

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.BasketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubtractProductFromBasket @Inject constructor(
    private val basketRepository: BasketRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): FlowUseCase<SubtractProductFromBasket.Params, BasketProduct>(ioDispatcher) {

    data class Params(
        val product:Product
    )

    override fun execute(params: Params): Flow<Try<BasketProduct>> =
        basketRepository.subtractProduct(params)
}