package com.grappim.cashier.data.remote.model.waybill

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WaybillProductDTO(
    val amount: BigDecimal,
    val barcode: String,
    @SerializedName("created_on")
    val createdOn: String,
    val id: Long,
    val name: String,
    @SerializedName("product_id")
    val productId: Long,
    @SerializedName("purchase_price")
    val purchasePrice: BigDecimal,
    @SerializedName("selling_price")
    val sellingPrice: BigDecimal,
    @SerializedName("updated_on")
    val updatedOn: String,
    @SerializedName("waybill_id")
    val waybillId: Int
)

data class PartialWaybillProductDTO(
    val id: Long? = null,
    val amount: BigDecimal,
    val barcode: String,
    val name: String,
    @SerializedName("purchase_price")
    val purchasePrice: BigDecimal,
    @SerializedName("selling_price")
    val sellingPrice: BigDecimal,
    @SerializedName("waybill_id")
    val waybillId: Int,
    @SerializedName("product_id")
    val productId: Long
)