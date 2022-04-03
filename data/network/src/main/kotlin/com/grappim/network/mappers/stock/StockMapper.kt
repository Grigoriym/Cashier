package com.grappim.network.mappers.stock

import com.grappim.domain.model.outlet.Stock
import com.grappim.network.model.stock.StockDTO

fun StockDTO.toDomain(): Stock =
    Stock(
        name = this.name,
        merchantId = this.merchantId,
        stockId = this.id
    )

fun List<StockDTO>.toDomain(): List<Stock> =
    ArrayList<Stock>(this.size).apply {
        this@toDomain.forEach {
            add(it.toDomain())
        }
    }