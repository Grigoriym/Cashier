package com.grappim.waybill.ui.product.model

import java.math.BigDecimal

sealed class WaybillProductStates {
    data class WaybillProductState(
        val type: WaybillProductType,
        val waybillId: Int,
        val productId: Long,
        val waybillProductId: Long?,

        val barcode: String,
        val name: String,

        val purchasePrice: BigDecimal,
        val purchasePriceToShow: String,
        val sellingPrice: BigDecimal,
        val sellingPriceToShow: String,
        val amount: BigDecimal,
        val amountToShow: String
    ) : WaybillProductStates()

    object EmptyState : WaybillProductStates()
}
