package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.payment.MakePaymentParams

interface PaymentRepository {

    suspend fun makePayment(params: MakePaymentParams): Try<Unit, Throwable>

}