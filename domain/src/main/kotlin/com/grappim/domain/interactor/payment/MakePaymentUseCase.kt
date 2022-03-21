package com.grappim.domain.interactor.payment

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.payment.PaymentMethodType
import com.grappim.domain.repository.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<MakePaymentUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val paymentMethodType: PaymentMethodType
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        paymentRepository.makePayment(params)
}