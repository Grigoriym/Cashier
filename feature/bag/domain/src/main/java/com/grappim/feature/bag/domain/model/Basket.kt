package com.grappim.feature.bag.domain.model

import java.math.BigDecimal

data class Basket(
    val id: Long,
    val name: String,
    val basketCount: BigDecimal,
    val amount: BigDecimal,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val barcode: String
)
