package com.grappim.network.mappers.outlet

import com.grappim.domain.model.outlet.Stock
import com.grappim.network.model.stock.StockDTO
import javax.inject.Inject

class StockMapper @Inject constructor(

) {

    fun dtoToDomain(from: StockDTO): Stock =
        Stock(
            name = from.name,
            merchantId = from.merchantId,
            stockId = from.id
        )

    fun dtoToDomainList(from: List<StockDTO>): List<Stock> =
        ArrayList<Stock>(from.size).apply {
            from.forEach {
                add(dtoToDomain(it))
            }
        }

}