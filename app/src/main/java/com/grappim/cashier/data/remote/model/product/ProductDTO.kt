package com.grappim.cashier.data.remote.model.product

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ProductDTO(
    val id: Long,
    val barcode: String,
    val name: String,
    @SerializedName("stock_id")
    val stockId: String,
    val amount: BigDecimal,
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
    val categoryId: Int,
    val category: String
)