package com.grappim.network.model.products

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CreateProductRequestDTO(
    val product: CreateProductRequestParamsDTO
)

data class CreateProductRequestParamsDTO(
    val name: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    val unit: String,
    @SerializedName("purchase_price")
    val purchasePrice: BigDecimal,
    @SerializedName("selling_price")
    val sellingPrice: BigDecimal,
    val amount: BigDecimal,
    val barcode: String,
    @SerializedName("created_on")
    val createdOn: String,
    @SerializedName("updated_on")
    val updatedOn: String,
    @SerializedName("category")
    val categoryName: String,
    @SerializedName("category_id")
    val categoryId: Int
)