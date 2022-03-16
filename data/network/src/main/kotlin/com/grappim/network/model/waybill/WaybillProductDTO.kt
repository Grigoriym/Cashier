package com.grappim.network.model.waybill

import com.grappim.common.network.serializers.BigDecimalSerializer
import com.grappim.common.network.serializers.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class WaybillProductDTO(
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("productId")
    val productId: Long,
    @SerialName("purchasePrice")
    @Serializable(BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @SerialName("sellingPrice")
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    @SerialName("updatedOn")
    @Serializable(LocalDateTimeSerializer::class)
    val updatedOn: LocalDateTime,
    @SerialName("createdOn")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime,
    @SerialName("waybillId")
    val waybillId: Int
)

@Serializable
data class PartialWaybillProductDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("name")
    val name: String,
    @SerialName("purchasePrice")
    @Serializable(BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @SerialName("sellingPrice")
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    @SerialName("waybillId")
    val waybillId: Long,
    @SerialName("productId")
    val productId: Long
)