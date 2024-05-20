package com.grappim.network.model.payment

import com.grappim.common.cashier.network.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateOrderDTO(
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("cashBoxId")
    val cashBoxId: String,
    @SerialName("totalSum")
    @Serializable(BigDecimalSerializer::class)
    val totalSum: BigDecimal,
    @SerialName("payType")
    val payType: String,
    @SerialName("orderItems")
    val orderItems: List<OrderItemDTO>
)

@Serializable
data class OrderDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("cashBoxId")
    val cashBoxId: String,
    @SerialName("totalSum")
    @Serializable(BigDecimalSerializer::class)
    val totalSum: BigDecimal,
    @SerialName("payType")
    val payType: String,
    @SerialName("orderItems")
    val orderItems: List<OrderItemDTO>
)

@Serializable
data class OrderItemDTO(
    @SerialName("productId")
    val productId: Long,
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("sellingPrice")
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    @SerialName("purchasePrice")
    @Serializable(BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("name")
    val name: String
)
