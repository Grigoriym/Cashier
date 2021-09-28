package com.grappim.domain.model.waybill

import java.math.BigDecimal

data class WaybillProduct(
    val amount: BigDecimal,
    val barcode: String,
    val createdOn: String,
    val id: Long,
    val name: String,
    val productId: Long,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val updatedOn: String,
    val waybillId: Int
)