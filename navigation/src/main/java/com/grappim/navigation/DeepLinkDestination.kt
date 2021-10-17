package com.grappim.navigation

import androidx.annotation.IdRes

internal sealed class DeepLinkDestination(
    val address: String,
    @IdRes val destinationId: Int,
    val popUpTo: Boolean = false
) {
    object PaymentMethodFlowToSalesFlow : DeepLinkDestination(
        address = "com.grappim.cashier://payment-method-to-sales",
        destinationId = R.id.sales_flow,
        popUpTo = true
    )
}
