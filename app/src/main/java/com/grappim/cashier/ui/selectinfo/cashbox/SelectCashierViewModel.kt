package com.grappim.cashier.ui.selectinfo.cashbox

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.cashier.GetCashBoxesUseCase
import com.grappim.cashier.domain.cashier.SaveCashierUseCase
import com.grappim.cashier.ui.selectinfo.stock.StockProgressItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCashierViewModel @Inject constructor(
    private val getCashBoxesUseCase: GetCashBoxesUseCase,
    private val saveCashierUseCase: SaveCashierUseCase
) : ViewModel() {

    val cashBoxes = mutableStateListOf<CashBox>()

    val cashBoxProgressItems = getOutletProgressItems()

    private var selectedCashBoxPosition by mutableStateOf(-1)
    val selectedCashBox: CashBox?
        get() = cashBoxes.getOrNull(selectedCashBoxPosition)

    var loading by mutableStateOf(false)

    init {
        getCashBoxes()
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
            saveCashierUseCase(cashBoxToSave)
        }
    }

    @MainThread
    fun getCashBoxes() {
        viewModelScope.launch {
            getCashBoxesUseCase()
                .onStart {
                    loading = true
                }
                .onCompletion {
                    loading = false
                }
                .catch {
//                    _cashBoxes.value = Resource.Error(it)
                }.collect {
                    cashBoxes.clear()
                    cashBoxes.addAll(it)
                }
        }
    }

    private fun getOutletProgressItems(): List<StockProgressItem> =
        listOf(
            StockProgressItem(R.string.outlet_selecting, true),
            StockProgressItem(R.string.outlet_checkout, true)
        )
}