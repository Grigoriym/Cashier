package com.grappim.domain.model

import com.grappim.calculations.bigDecimalOne
import java.math.BigDecimal

data class BasketProduct(
    val id: Long,
    val barcode: String,
    val name: String,
    val productId: Long,
    val stockId: String,
    val merchantId: String,
    val amount: BigDecimal,
    val unit: ProductUnit,
    val sellingPrice: BigDecimal,
    val basketId: Long
) {
    companion object {
        fun empty(): BasketProduct = BasketProduct(
            id = 0,
            barcode = "",
            name = "",
            productId = 0,
            stockId = "",
            merchantId = "",
            amount = bigDecimalOne(),
            unit = ProductUnit.KG,
            sellingPrice = bigDecimalOne(),
            basketId = 0
        )
    }
}
