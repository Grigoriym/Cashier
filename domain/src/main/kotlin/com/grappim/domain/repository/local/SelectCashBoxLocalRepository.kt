package com.grappim.domain.repository.local

import com.grappim.domain.model.cashbox.CashBox

interface SelectCashBoxLocalRepository {

    fun clear()

    fun setCashBoxes(list: List<CashBox>)

    fun setSelectedCashBox(cashBox: CashBox)

    fun getSelectedCashBox(): CashBox?

    fun getCashBoxes(): List<CashBox>

}