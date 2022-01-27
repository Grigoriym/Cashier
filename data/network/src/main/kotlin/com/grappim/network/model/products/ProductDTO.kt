package com.grappim.network.model.products

import com.grappim.common.asynchronous.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProductDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("barcode")
    val barcode: String,
    @SerialName("name")
    val name: String,
    @SerialName("stock_id")
    val stockId: String,
    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    @SerialName("unit")
    val unit: String,
    @SerialName("purchase_price")
    @Serializable(BigDecimalSerializer::class)
    val purchasePrice: BigDecimal,
    @SerialName("selling_price")
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal,
    @SerialName("merchant_id")
    val merchantId: String,
    @SerialName("created_on")
    val createdOn: String,
    @SerialName("updated_on")
    val updatedOn: String,
    @SerialName("category_id")
    val categoryId: Long,
    @SerialName("category")
    val category: String
)