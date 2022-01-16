package com.grappim.stock.model

import androidx.annotation.StringRes

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)