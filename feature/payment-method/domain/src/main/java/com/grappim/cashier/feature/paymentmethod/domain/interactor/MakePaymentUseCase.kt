package com.grappim.cashier.feature.paymentmethod.domain.interactor

import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.feature.paymentmethod.domain.repository.PaymentRepository
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {

    suspend fun execute(params: MakePaymentParams): Try<Unit, Throwable> =
        paymentRepository.makePayment(params)
}
