package com.grappim.network.model.products

import com.grappim.common.network.serializers.BigDecimalSerializer
import com.grappim.common.network.serializers.LocalDateTimeSerializer
import com.grappim.domain.model.base.ProductUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class ProductDTO(
    val id: Long,
    val barcode: String,
    val name: String,
    val stockId: String,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    val unit: String,
    val merchantId: String,
    @Serializable(with = BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    val createdOn: String,
    val updatedOn: String,
    val categoryId: Long
)