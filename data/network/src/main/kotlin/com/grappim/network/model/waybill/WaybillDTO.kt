package com.grappim.network.model.waybill

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WaybillDTO(
    @SerializedName("created_on")
    val createdOn: String,
    val id: Int,
    @SerializedName("merchant_id")
    val merchantId: String,
    val number: String,
    val status: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("total_cost")
    val totalCost: BigDecimal,
    val type: String,
    @SerializedName("updated_on")
    val updatedOn: String,
    @SerializedName("reserved_time")
    val reservedTime: String?,
    val comment: String
)