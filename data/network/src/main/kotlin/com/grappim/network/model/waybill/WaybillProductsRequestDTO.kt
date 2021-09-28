package com.grappim.network.model.waybill


import com.google.gson.annotations.SerializedName

data class WaybillProductsRequestDTO(
  @SerializedName("limit")
  val limit: Int,
  @SerializedName("offset")
  val offset: Int,
  @SerializedName("waybill_id")
  val waybillId: Int
)