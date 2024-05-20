package com.grappim.feature.waybill.network.model

import com.grappim.common.cashier.network.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateWaybillProductResponseDTO(
    @SerialName("totalCost")
    @Serializable(BigDecimalSerializer::class)
    val totalCost: BigDecimal
)
