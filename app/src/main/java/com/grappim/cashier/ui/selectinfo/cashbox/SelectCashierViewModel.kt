package com.grappim.cashier.ui.selectinfo.cashbox

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.cashier.GetCashBoxesUseCase
import com.grappim.cashier.domain.cashier.SaveCashierUseCase
import com.grappim.cashier.ui.selectinfo.stock.StockProgressItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCashierViewModel @Inject constructor(
  private val getCashBoxesUseCase: GetCashBoxesUseCase,
  private val saveCashierUseCase: SaveCashierUseCase
) : ViewModel() {

  private val _cashBoxes = MutableStateFlow<Resource<List<CashBox>>>(Resource.Loading)
  val cashBoxes: StateFlow<Resource<List<CashBox>>>
    get() = _cashBoxes

  val stockProgresses: List<StockProgressItem> = getOutletProgressItems()

  init {
    getCashBoxes()
  }

  fun saveCashBox(cashBox: CashBox) {
    viewModelScope.launch {
      saveCashierUseCase.invoke(cashBox)
    }
  }

  @MainThread
  fun getCashBoxes() {
    viewModelScope.launch {
      getCashBoxesUseCase()
        .onStart {
          _cashBoxes.value = Resource.Loading
        }
        .catch {
          _cashBoxes.value = Resource.Error(it)
        }.collect {
          _cashBoxes.value = Resource.Success(it)
        }
    }
  }

  private fun getOutletProgressItems(): List<StockProgressItem> =
    listOf(
      StockProgressItem(R.string.outlet_selecting, true),
      StockProgressItem(R.string.outlet_checkout, true),
      StockProgressItem(R.string.title_empty, true)
    )
}