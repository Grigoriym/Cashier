package com.grappim.network.model.stock

import com.google.gson.annotations.SerializedName

data class GetStocksResponseDTO(
    @SerializedName("stocks")
    val stocks: List<StockDTO>
)