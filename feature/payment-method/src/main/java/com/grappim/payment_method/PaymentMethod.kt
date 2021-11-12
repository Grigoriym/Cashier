package com.grappim.payment_method

import androidx.annotation.StringRes
import com.grappim.domain.model.payment.PaymentMethodType

data class PaymentMethod(
    @StringRes val text: Int,
    val type: PaymentMethodType
)
