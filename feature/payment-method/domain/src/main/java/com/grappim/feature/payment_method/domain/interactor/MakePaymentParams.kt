package com.grappim.feature.payment_method.domain.interactor

import com.grappim.feature.payment_method.domain.model.PaymentMethodType

data class MakePaymentParams(
    val paymentMethodType: PaymentMethodType
)
