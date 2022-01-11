package com.grappim.cashbox.model

import androidx.annotation.StringRes

data class CashierProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)