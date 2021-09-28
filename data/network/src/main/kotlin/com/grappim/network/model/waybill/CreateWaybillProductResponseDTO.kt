package com.grappim.network.model.waybill

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CreateWaybillProductResponseDTO(
    @SerializedName("total_cost")
    val totalCost: BigDecimal
)
