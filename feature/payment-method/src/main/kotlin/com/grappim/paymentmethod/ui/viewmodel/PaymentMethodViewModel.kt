package com.grappim.paymentmethod.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.paymentmethod.model.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

abstract class PaymentMethodViewModel : BaseViewModel() {

    abstract val paymentItems: List<PaymentMethod>
    abstract val basketCount: StateFlow<String>
    abstract val basketSum: StateFlow<String>

    abstract fun makePayment(paymentMethod: PaymentMethod)
}
