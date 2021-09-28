package com.grappim.domain.model.basket

import java.math.BigDecimal

data class BasketProduct(
    val id: Long,
    val name: String,
    val basketCount: BigDecimal,
    val amount: BigDecimal,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val barcode: String
)