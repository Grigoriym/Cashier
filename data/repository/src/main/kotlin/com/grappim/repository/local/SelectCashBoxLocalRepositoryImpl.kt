package com.grappim.repository.local

import com.grappim.common.di.AppScope
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import javax.inject.Inject

@AppScope
class SelectCashBoxLocalRepositoryImpl @Inject constructor(

) : SelectCashBoxLocalRepository {

    private val cashBoxes = mutableListOf<CashBox>()
    private var selectedCashBoxPosition: Int = -1

    override fun clear() {
        cashBoxes.clear()
        selectedCashBoxPosition = -1
    }

    override fun setCashBoxes(list: List<CashBox>) {
        cashBoxes.clear()
        cashBoxes.addAll(list)
    }

    override fun setSelectedCashBox(cashBox: CashBox) {
        selectedCashBoxPosition = if (selectedCashBoxPosition == -1 ||
            selectedCashBoxPosition != cashBoxes.indexOf(cashBox)
        ) {
            cashBoxes.indexOf(cashBox)
        } else {
            -1
        }
    }

    override fun getSelectedCashBox(): CashBox? = cashBoxes.getOrNull(selectedCashBoxPosition)

    override fun getCashBoxes(): List<CashBox> = cashBoxes.toList()
}