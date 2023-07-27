package com.grappim.feature.waybill.domain.model

import com.grappim.calculations.bigDecimalOne
import java.io.Serializable
import java.math.BigDecimal

data class Waybill(
    val id: Long,
    val createdOn: String,
    val merchantId: String,
    val number: String,
    val status: WaybillStatus,
    val stockId: String,
    val totalCost: BigDecimal,
    val updatedOn: String,
    var reservedTime: String?,
    var reservedTimeToDemonstrate: String?,
    val comment: String,
    val updateOnToDemonstrate: String
) : Serializable {
    companion object {
        fun empty(): Waybill =
            Waybill(
                id = 1,
                createdOn = "",
                merchantId = "",
                number = "2312312",
                status = WaybillStatus.ACTIVE,
                stockId = "",
                totalCost = bigDecimalOne(),
                updatedOn = "23.12.23.20123",
                reservedTime = "",
                comment = "",
                updateOnToDemonstrate = "23.12.23.20123",
                reservedTimeToDemonstrate = "23.12.23.20123"
            )
    }
}
