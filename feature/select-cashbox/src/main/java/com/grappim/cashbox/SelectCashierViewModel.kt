package com.grappim.cashbox

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.domain.base.Try
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.cashier.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashier.SaveCashierUseCase
import com.grappim.domain.model.cashbox.CashBox
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
            saveCashierUseCase(SaveCashierUseCase.Params(cashBoxToSave))
        }
    }

    @MainThread
    fun getCashBoxes() {
        viewModelScope.launch {
            getCashBoxesUseCase(withoutParams())
                .collect {
                    loading = it is Try.Loading

                    when (it) {
                        is Try.Success -> {
                            cashBoxes.clear()
                            cashBoxes.addAll(it.data)
                        }
                        is Try.Error -> {

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