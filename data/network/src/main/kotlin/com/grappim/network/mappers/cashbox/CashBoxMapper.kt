package com.grappim.network.mappers.cashbox

import com.grappim.domain.model.cashbox.CashBox
import com.grappim.network.model.cashbox.CashBoxDTO

fun CashBoxDTO.toDomain(): CashBox = CashBox(
    name = this.name,
    cashBoxId = this.cashBoxId,
    merchantId = this.merchantId,
    stockId = this.stockId
)

fun List<CashBoxDTO>.toDomain(): List<CashBox> = ArrayList<CashBox>(this.size).apply {
    this@toDomain.forEach {
        add(it.toDomain())
    }
}
