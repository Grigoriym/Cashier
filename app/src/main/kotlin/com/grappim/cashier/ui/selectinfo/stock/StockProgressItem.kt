package com.grappim.cashier.ui.selectinfo.stock

import androidx.annotation.StringRes

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)