package com.grappim.domain.model.order

import java.math.BigDecimal

data class Order(
    val id: Int,
    val timestamp: String,
    val merchantId: String,
    val stockId: String,
    val cashBoxId: String,
    val totalSum: BigDecimal,
    val payType: String,
    val orderItems: List<OrderItem>
)

data class OrderItem(
    val productId: Int,
    val amount: BigDecimal,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal
)
