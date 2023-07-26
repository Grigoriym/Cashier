package com.grappim.cashbox.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grappim.uikit.R
import com.grappim.cashbox.model.CashierProgressItem
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.cashbox.getCashbox.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashbox.saveCashbox.SaveCashBoxParams
import com.grappim.domain.interactor.cashbox.saveCashbox.SaveCashBoxUseCase
import com.grappim.domain.model.cashbox.CashBox
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCashBoxViewModelImpl @Inject constructor(
    private val getCashBoxesUseCase: GetCashBoxesUseCase,
    private val saveCashBoxUseCase: SaveCashBoxUseCase
) : SelectCashBoxViewModel() {

    override val cashBoxes = mutableStateListOf<CashBox>()
    override val cashBoxProgressItems = getProgressItems()

    private var selectedCashBoxPosition by mutableStateOf(-1)
    override val selectedCashBox
        get() = cashBoxes.getOrNull(selectedCashBoxPosition)

    init {
        getCashBoxes()
    }

    override fun selectCashBox(cashBox: CashBox) {
        selectedCashBoxPosition = if (selectedCashBoxPosition == -1 ||
            selectedCashBoxPosition != cashBoxes.indexOf(cashBox)
        ) {
            cashBoxes.indexOf(cashBox)
        } else {
            -1
        }
    }

    override fun saveCashBox() {
        viewModelScope.launch {
            val cashBoxToSave = requireNotNull(selectedCashBox) {
                "CashBox must not be null"
            }
            saveCashBoxUseCase.execute(SaveCashBoxParams(cashBoxToSave))
        }
    }

    override fun showMenu() {
        flowRouter.goToMenu()
    }

    override fun getCashBoxes() {
        viewModelScope.launch {
            _loading.value = true
            val result = getCashBoxesUseCase.execute()
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    cashBoxes.clear()
                    cashBoxes.addAll(result.result)
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    private fun getProgressItems(): List<CashierProgressItem> =
        listOf(
            CashierProgressItem(R.string.outlet_selecting, true),
            CashierProgressItem(R.string.outlet_checkout, true)
        )
}