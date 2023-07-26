package com.grappim.payment_method.helper

import com.grappim.feature.payment_method.domain.model.PaymentMethodType
import com.grappim.payment_method.model.PaymentMethod
import com.grappim.uikit.R
import javax.inject.Inject

class PaymentMethodItemGenerator @Inject constructor(

) {

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