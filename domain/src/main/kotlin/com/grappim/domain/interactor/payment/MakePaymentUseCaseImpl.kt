package com.grappim.domain.interactor.payment

import com.grappim.common.lce.Try
import com.grappim.domain.repository.PaymentRepository
import javax.inject.Inject

class MakePaymentUseCaseImpl @Inject constructor(
    private val paymentRepository: PaymentRepository
) : MakePaymentUseCase {

    override suspend fun execute(params: MakePaymentParams): Try<Unit, Throwable> =
        paymentRepository.makePayment(params)
}