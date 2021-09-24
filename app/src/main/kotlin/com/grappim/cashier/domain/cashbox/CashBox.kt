package com.grappim.cashier.domain.cashbox

data class CashBox(
    val name: String,
    val cashBoxId: String,
    val merchantId: String,
    val stockId: String
)