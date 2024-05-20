package com.grappim.products.network.model

import com.grappim.common.cashier.network.serializers.BigDecimalSerializer
import com.grappim.domain.model.ProductUnit
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProductDTO(
    val id: Long,
    val barcode: String,
    val name: String,
    val stockId: String,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    val unit: ProductUnit,
    val merchantId: String,
    @Serializable(with = BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    val createdOn: String,
    val updatedOn: String,
    val categoryId: Long
)
