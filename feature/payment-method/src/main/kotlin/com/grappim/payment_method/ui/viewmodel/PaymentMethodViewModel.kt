package com.grappim.payment_method.ui.viewmodel

import com.grappim.core.base.BaseViewModel2
import com.grappim.payment_method.model.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

abstract class PaymentMethodViewModel : BaseViewModel2() {

    abstract val paymentItems: List<PaymentMethod>
    abstract val basketCount: StateFlow<String>
    abstract val basketSum: StateFlow<String>

    abstract fun makePayment(paymentMethod: PaymentMethod)
}