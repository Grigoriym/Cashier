package com.grappim.domain.model.waybill

import java.io.Serializable
import java.math.BigDecimal

data class Waybill(
    val id: Int,
    val createdOn: String,
    val merchantId: String,
    val number: String,
    val status: WaybillStatus,
    val stockId: String,
    val totalCost: BigDecimal,
    val type: WaybillType,
    val updatedOn: String,
    var reservedTime: String?,
    var reservedTimeToDemonstrate: String?,
    val comment: String,
    val updateOnToDemonstrate: String
) : Serializable