package com.grappim.cashier.data.remote.model.cashbox

import com.grappim.cashier.domain.cashbox.CashBox

object CashBoxMapper {

    fun List<CashBoxDTO>.toDomain(): List<CashBox> {
        val resultList = mutableListOf<CashBox>()
        this.forEach {
            resultList.add(it.toDomain())
        }
        return resultList
    }

    private fun CashBoxDTO.toDomain(): CashBox =
        CashBox(
            name = this.name,
            cashBoxId = this.cashBoxId,
            merchantId = this.merchantId,
            stockId = this.stockId
        )
}