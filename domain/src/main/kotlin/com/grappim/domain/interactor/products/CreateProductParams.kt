package com.grappim.domain.interactor.products

import java.math.BigDecimal

data class CreateProductParams(
    val name: String,
    val stockId: String,
    val merchantId: String,
    val unit: String,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val amount: BigDecimal,
    val barcode: String,
    val categoryName: String,
    val categoryId: Long
)