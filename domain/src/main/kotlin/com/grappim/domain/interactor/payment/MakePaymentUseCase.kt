package com.grappim.domain.interactor.payment

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
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

    override fun execute(params: Params): Flow<Result<Unit>> =
        paymentRepository.makePayment(params)
}