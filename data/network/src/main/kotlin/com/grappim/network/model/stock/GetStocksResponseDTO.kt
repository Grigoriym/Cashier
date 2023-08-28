package com.grappim.network.model.stock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStocksResponseDTO(
    @SerialName("stocks")
    val stocks: List<StockDTO>
)
