package com.grappim.domain.model.waybill

import com.grappim.calculations.asBigDecimal
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

data class WaybillProduct(
    val amount: BigDecimal,
    val barcode: String,
    val id: Long,
    val name: String,
    val productId: Long,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val updatedOn: LocalDateTime,
    val createdOn: LocalDateTime,
    val waybillId: Int
) : Serializable {
    companion object {
        fun getExample() =
            WaybillProduct(
                amount = "100".asBigDecimal(),
                barcode = "12345",
                createdOn = LocalDateTime.now(),
                id = 1L,
                name = "Product name",
                productId = 2L,
                purchasePrice = "234".asBigDecimal(),
                sellingPrice = "4532".asBigDecimal(),
                updatedOn = LocalDateTime.now(),
                waybillId = 45
            )
    }
}