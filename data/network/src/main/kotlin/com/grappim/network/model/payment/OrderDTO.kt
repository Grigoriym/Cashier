package com.grappim.network.model.payment

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CreateOrderDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("cash_box_id")
    val cashBoxId: String,
    @SerializedName("total_sum")
    val totalSum: BigDecimal,
    @SerializedName("pay_type")
    val payType: String,
    @SerializedName("order_items")
    val orderItems: List<OrderItemDTO>
)

data class OrderDTO(
    val id: Int,
    val timestamp: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("cash_box_id")
    val cashBoxId: String,
    @SerializedName("total_sum")
    val totalSum: BigDecimal,
    @SerializedName("pay_type")
    val payType: String,
    @SerializedName("order_items")
    val orderItems: List<OrderItemDTO>
)

data class OrderItemDTO(
    @SerializedName("product_id")
    val productId: Long,
    val amount: BigDecimal,
    @SerializedName("selling_price")
    val sellingPrice: BigDecimal,
    @SerializedName("purchase_price")
    val purchasePrice: BigDecimal
//    val barcode: String
)