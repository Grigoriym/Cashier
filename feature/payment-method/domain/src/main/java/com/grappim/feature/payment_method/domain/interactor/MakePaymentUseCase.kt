package com.grappim.feature.payment_method.domain.interactor

import com.grappim.common.lce.Try
import com.grappim.feature.payment_method.domain.repository.PaymentRepository
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {

    suspend fun execute(params: MakePaymentParams): Try<Unit, Throwable> =
        paymentRepository.makePayment(params)
}