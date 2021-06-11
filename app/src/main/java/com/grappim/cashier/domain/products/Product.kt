package com.grappim.cashier.domain.products

import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.utils.ProductUnit
import java.math.BigDecimal

data class Product(
    val id: Long,
    val barcode: String,
    val name: String,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val amount: BigDecimal,
    val stockId: String,
    val unit: ProductUnit,
    val merchantId: String,

    val createdOn: String,
    val updatedOn: String,

    val categoryId: String? = null,

    var basketCount: BigDecimal = bigDecimalZero()
)