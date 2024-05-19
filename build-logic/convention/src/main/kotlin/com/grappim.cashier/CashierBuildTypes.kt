package com.grappim.cashier

enum class CashierBuildTypes(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
