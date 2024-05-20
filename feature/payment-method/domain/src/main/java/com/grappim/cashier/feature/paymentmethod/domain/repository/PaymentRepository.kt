package com.grappim.cashier.feature.paymentmethod.domain.repository

import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.feature.paymentmethod.domain.interactor.MakePaymentParams

interface PaymentRepository {
    suspend fun makePayment(params: MakePaymentParams): Try<Unit, Throwable>
}
