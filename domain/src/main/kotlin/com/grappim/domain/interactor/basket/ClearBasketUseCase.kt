package com.grappim.domain.interactor.basket

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.common.lce.Try
import com.grappim.domain.repository.BasketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClearBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, NoParams>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Try<NoParams>> =
        basketRepository.clearBasket()

}