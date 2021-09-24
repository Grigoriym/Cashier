package com.grappim.cashier.data.remote.model.outlet

import com.grappim.cashier.domain.outlet.Stock

object OutletMapper {

    fun List<OutletDTO>.toDomain(): List<Stock> {
        val outletList = mutableListOf<Stock>()
        this.forEach {
            outletList.add(it.toDomain())
        }
        return outletList
    }

    fun OutletDTO.toDomain(): Stock =
        Stock(
            name = this.stockName,
            merchantId = this.merchantId,
            stockId = this.stockId
        )
}