package com.grappim.cashbox.ui.viewmodel

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grappim.cashbox.R
import com.grappim.cashbox.model.CashierProgressItem
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.navigation.AppRouter
import com.grappim.domain.interactor.cashier.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.model.cashbox.CashBox
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCashBoxViewModelImpl @Inject constructor(
    private val getCashBoxesUseCase: GetCashBoxesUseCase,
    private val saveCashBoxUseCase: SaveCashBoxUseCase,
    private val appRouter: AppRouter
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
            saveCashBoxUseCase(SaveCashBoxUseCase.Params(cashBoxToSave))
        }
    }

    override fun showMenu() {
        appRouter.goToMenu()
    }

    @MainThread
    override fun getCashBoxes() {
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

    private fun getProgressItems(): List<CashierProgressItem> =
        listOf(
            CashierProgressItem(R.string.outlet_selecting, true),
            CashierProgressItem(R.string.outlet_checkout, true)
        )
}