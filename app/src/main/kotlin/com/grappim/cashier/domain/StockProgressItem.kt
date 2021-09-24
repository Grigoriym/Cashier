package com.grappim.cashier.domain

import androidx.annotation.StringRes

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)