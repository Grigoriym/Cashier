package com.grappim.stock

import androidx.annotation.StringRes

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)