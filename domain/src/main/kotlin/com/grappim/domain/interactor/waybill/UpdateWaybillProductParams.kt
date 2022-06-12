package com.grappim.domain.interactor.waybill

import java.math.BigDecimal

data class UpdateWaybillProductParams(
    val waybillId: Long,
    val barcode: String,
    val name: String,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val amount: BigDecimal,
    val productId: Long,
    val id: Long
)
