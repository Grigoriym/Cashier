package com.grappim.network.model.products

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ProductDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("amount")
    val amount: BigDecimal,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("purchase_price")
    val purchasePrice: BigDecimal,
    @SerializedName("selling_price")
    val sellingPrice: BigDecimal,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("created_on")
    val createdOn: String,
    @SerializedName("updated_on")
    val updatedOn: String,
    @SerializedName("category_id")
    val categoryId: Long,
    @SerializedName("category")
    val category: String
)