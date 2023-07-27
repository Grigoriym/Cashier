package com.grappim.products.network.model

import com.grappim.common.network.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateProductRequestDTO(
    @SerialName("product")
    val product: CreateProductRequestParamsDTO
)

@Serializable
data class CreateProductRequestParamsDTO(
    @SerialName("name")
    val name: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("unit")
    val unit: String,
    @SerialName("purchasePrice")
    @Serializable(BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @SerialName("sellingPrice")
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("categoryId")
    val categoryId: Long
)
