package com.grappim.cashier.feature.paymentmethod.presentation.model

import androidx.annotation.StringRes
import com.grappim.cashier.feature.paymentmethod.domain.model.PaymentMethodType

data class PaymentMethod(
    @StringRes val text: Int,
    val type: PaymentMethodType
)
