package com.grappim.cashbox.ui.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.grappim.cashbox.model.CashierProgressItem
import com.grappim.core.base.BaseViewModel2
import com.grappim.domain.model.cashbox.CashBox

abstract class SelectCashBoxViewModel : BaseViewModel2() {

    abstract val cashBoxProgressItems: List<CashierProgressItem>
    abstract val selectedCashBox: CashBox?
    abstract val cashBoxes: SnapshotStateList<CashBox>

    abstract fun selectCashBox(cashBox: CashBox)
    abstract fun saveCashBox()
    abstract fun showMenu()
    abstract fun getCashBoxes()
}