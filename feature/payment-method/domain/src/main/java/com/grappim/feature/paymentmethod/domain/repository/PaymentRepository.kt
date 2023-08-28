package com.grappim.feature.paymentmethod.domain.repository

import com.grappim.common.lce.Try
import com.grappim.feature.paymentmethod.domain.interactor.MakePaymentParams

interface PaymentRepository {

    suspend fun makePayment(params: MakePaymentParams): Try<Unit, Throwable>

}
