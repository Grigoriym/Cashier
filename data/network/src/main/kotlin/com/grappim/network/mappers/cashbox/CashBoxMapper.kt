package com.grappim.network.mappers.cashbox

import com.grappim.domain.model.cashbox.CashBox
import com.grappim.network.model.cashbox.CashBoxDTO
import javax.inject.Inject

class CashBoxMapper @Inject constructor(

) {

    suspend fun dtoToDomain(from: CashBoxDTO): CashBox =
        CashBox(
            name = from.name,
            cashBoxId = from.cashBoxId,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    suspend fun dtoToDomainList(from: List<CashBoxDTO>): List<CashBox> =
        ArrayList<CashBox>(from.size).apply {
            from.forEach {
                add(dtoToDomain(it))
            }
        }

}