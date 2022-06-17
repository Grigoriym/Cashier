package com.grappim.feature.waybill.domain.interactor.createWaybillProduct

import java.math.BigDecimal

data class CreateWaybillProductParams(
    val waybillId: Long,
    val barcode: String,
    val name: String,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val amount: BigDecimal,
    val productId: Long
)
