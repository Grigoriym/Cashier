package com.grappim.cashier.feature.paymentmethod.presentation.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.cashier.feature.paymentmethod.presentation.model.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

abstract class PaymentMethodViewModel : BaseViewModel() {

    abstract val paymentItems: List<PaymentMethod>
    abstract val basketCount: StateFlow<String>
    abstract val basketSum: StateFlow<String>

    abstract fun makePayment(paymentMethod: PaymentMethod)
}
