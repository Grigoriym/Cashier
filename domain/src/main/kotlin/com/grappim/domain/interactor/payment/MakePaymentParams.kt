package com.grappim.domain.interactor.payment

import com.grappim.domain.model.payment.PaymentMethodType

data class MakePaymentParams(
    val paymentMethodType: PaymentMethodType
)
