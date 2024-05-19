package com.grappim.cashier.feature.paymentmethod.presentation.helper

import com.grappim.cashier.feature.paymentmethod.presentation.R
import com.grappim.cashier.feature.paymentmethod.presentation.model.PaymentMethod
import com.grappim.feature.paymentmethod.domain.model.PaymentMethodType
import javax.inject.Inject

class PaymentMethodItemGenerator @Inject constructor() {
    val paymentMethodItems: List<PaymentMethod> =
        listOf(
            PaymentMethod(
                text = R.string.title_cash_payment,
                type = PaymentMethodType.CASH
            ),
            PaymentMethod(
                text = R.string.title_card_payment,
                type = PaymentMethodType.CARD
            )
        )
}
