package com.grappim.cashier.feature.paymentmethod.domain.interactor

import com.grappim.cashier.feature.paymentmethod.domain.model.PaymentMethodType

data class MakePaymentParams(
    val paymentMethodType: PaymentMethodType
)
