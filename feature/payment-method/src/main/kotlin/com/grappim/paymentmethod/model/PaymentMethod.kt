package com.grappim.paymentmethod.model

import androidx.annotation.StringRes
import com.grappim.feature.paymentmethod.domain.model.PaymentMethodType

data class PaymentMethod(
    @StringRes val text: Int,
    val type: PaymentMethodType
)
