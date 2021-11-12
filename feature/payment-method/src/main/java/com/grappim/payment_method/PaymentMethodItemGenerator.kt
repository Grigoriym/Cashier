package com.grappim.payment_method

import com.grappim.domain.model.payment.PaymentMethodType
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