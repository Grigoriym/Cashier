package com.grappim.domain.model.product

import com.grappim.calculations.bigDecimalZero
import com.grappim.domain.model.base.ProductUnit
import java.io.Serializable
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

    val categoryId: Int? = null,

    var basketCount: BigDecimal = bigDecimalZero()
) : Serializable