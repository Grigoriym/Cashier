package com.grappim.cashier.data.remote.model.waybill

import com.google.gson.annotations.SerializedName

data class FilterWaybillsRequestDTO(
  val limit: Int,
  @SerializedName("merchant_id")
  val merchantId: String,
  val offset: Int,
  @SerializedName("stock_id")
  val stockId: String,
  @SerializedName("waybill_type")
  val waybillType: String
)