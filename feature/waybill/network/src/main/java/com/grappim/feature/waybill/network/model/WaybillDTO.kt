package com.grappim.feature.waybill.network.model

import com.grappim.common.network.serializers.BigDecimalSerializer
import com.grappim.feature.waybill.domain.model.WaybillStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class WaybillDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("number")
    val number: String,
    @SerialName("status")
    val status: com.grappim.feature.waybill.domain.model.WaybillStatus,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("totalCost")
    @Serializable(BigDecimalSerializer::class)
    val totalCost: BigDecimal,
//    @SerialName("type")
//    val type: String,
    @SerialName("updatedOn")
    val updatedOn: String,
    @SerialName("createdOn")
    val createdOn: String,
    @SerialName("reservedTime")
    val reservedTime: String?,
    @SerialName("comment")
    val comment: String
)