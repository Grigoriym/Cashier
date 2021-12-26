package com.grappim.navigation

import androidx.annotation.IdRes

sealed class DeepLinkDestination(
    val address: String,
    @IdRes val destinationId: Int,
    val popUpTo: Boolean = false
) {
    object PaymentMethodFlowToSalesFlow : DeepLinkDestination(
        address = "com.grappim.cashier://payment-method-to-sales",
        destinationId = R.id.sales_flow,
        popUpTo = true
    )

    object RegisterToAuthFlow : DeepLinkDestination(
        address = "com.grappim.cashier://sign-up-to-auth",
        destinationId = R.id.auth_flow,
        popUpTo = true
    )
}
