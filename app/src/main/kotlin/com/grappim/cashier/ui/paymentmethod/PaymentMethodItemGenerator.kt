package com.grappim.cashier.ui.paymentmethod

import com.grappim.cashier.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentMethodItemGenerator @Inject constructor(

) {

    val paymentMethodItems: Flow<List<PaymentMethod>> = flow {
        val items = mutableListOf<PaymentMethod>()
        items.add(
            PaymentMethod(
                text = R.string.title_cash_payment,
                type = PaymentMethodType.CASH
            )
        )
        items.add(
            PaymentMethod(
                text = R.string.title_card_payment,
                type = PaymentMethodType.CARD
            )
        )
        emit(items)
    }
}