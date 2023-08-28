package com.grappim.feature.paymentmethod.domain.interactor

import com.grappim.feature.paymentmethod.domain.model.PaymentMethodType

data class MakePaymentParams(
    val paymentMethodType: PaymentMethodType
)
