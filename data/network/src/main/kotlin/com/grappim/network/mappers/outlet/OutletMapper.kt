package com.grappim.network.mappers.outlet

import com.grappim.domain.model.outlet.Stock
import com.grappim.network.model.outlet.OutletDTO
import javax.inject.Inject

class OutletMapper @Inject constructor(

) {

    fun dtoToDomain(from: OutletDTO): Stock =
        Stock(
            name = from.stockName,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    fun dtoToDomainList(from: List<OutletDTO>): List<Stock> =
        ArrayList<Stock>(from.size).apply {
            from.forEach {
                add(dtoToDomain(it))
            }
        }

}