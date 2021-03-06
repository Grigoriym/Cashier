package com.grappim.feature.payment_method.domain.repository

import com.grappim.common.lce.Try
import com.grappim.feature.payment_method.domain.interactor.MakePaymentParams

interface PaymentRepository {

    suspend fun makePayment(params: MakePaymentParams): Try<Unit, Throwable>

}