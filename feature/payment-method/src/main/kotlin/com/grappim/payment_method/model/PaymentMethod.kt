package com.grappim.payment_method.model

import androidx.annotation.StringRes
import com.grappim.feature.payment_method.domain.model.PaymentMethodType

data class PaymentMethod(
    @StringRes val text: Int,
    val type: PaymentMethodType
)
