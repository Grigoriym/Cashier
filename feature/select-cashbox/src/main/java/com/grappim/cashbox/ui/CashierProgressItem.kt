package com.grappim.cashbox.ui

import androidx.annotation.StringRes

data class CashierProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)