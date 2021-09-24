package com.grappim.cashier.data.remote.model.waybill

import com.google.gson.annotations.SerializedName

data class WaybillProductsResponseDTO(
  @SerializedName("products")
  val products: List<WaybillProductDTO>?
)