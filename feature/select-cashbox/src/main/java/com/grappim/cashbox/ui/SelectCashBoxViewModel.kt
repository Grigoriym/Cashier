package com.grappim.cashbox.ui

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grappim.cashbox.R
import com.grappim.core.BaseViewModel
import com.grappim.domain.base.Try
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.cashier.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.navigation.directions.select_cashier.SelectCashBoxNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCashBoxViewModel @Inject constructor(
    private val getCashBoxesUseCase: GetCashBoxesUseCase,
    private val saveCashBoxUseCase: SaveCashBoxUseCase,
    private val selectCashBoxNavigator: SelectCashBoxNavigator
) : BaseViewModel() {

    val cashBoxes = mutableStateListOf<CashBox>()

    val cashBoxProgressItems = getOutletProgressItems()

    private var selectedCashBoxPosition by mutableStateOf(-1)
    val selectedCashBox: CashBox?
        get() = cashBoxes.getOrNull(selectedCashBoxPosition)

    init {
        getCashBoxes()
    }

    fun onBackPressed() {
        selectCashBoxNavigator.goBack()
    }

    fun selectCashBox(cashBox: CashBox) {
        selectedCashBoxPosition = if (selectedCashBoxPosition == -1 ||
            selectedCashBoxPosition != cashBoxes.indexOf(cashBox)
        ) {
            cashBoxes.indexOf(cashBox)
        } else {
            -1
        }
    }

    fun saveCashBox() {
        viewModelScope.launch {
            val cashBoxToSave = requireNotNull(selectedCashBox) {
                "CashBox must not be null"
            }
            saveCashBoxUseCase(SaveCashBoxUseCase.Params(cashBoxToSave))
        }
    }

    fun showMenu() {
        selectCashBoxNavigator.goToMenu()
    }

    @MainThread
    fun getCashBoxes() {
        viewModelScope.launch {
            getCashBoxesUseCase(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading

                    when (it) {
                        is Try.Success -> {
                            cashBoxes.clear()
                            cashBoxes.addAll(it.data)
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    private fun getOutletProgressItems(): List<CashierProgressItem> =
        listOf(
            CashierProgressItem(R.string.outlet_selecting, true),
            CashierProgressItem(R.string.outlet_checkout, true)
        )
}