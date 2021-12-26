package com.grappim.stock.ui

import androidx.annotation.StringRes

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)