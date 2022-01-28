package com.grappim.network.model.waybill

import com.grappim.common.network.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class WaybillProductDTO(
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("createdOn")
    val createdOn: String,
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
    val updatedOn: String,
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
    val waybillId: Int,
    @SerialName("productId")
    val productId: Long
)