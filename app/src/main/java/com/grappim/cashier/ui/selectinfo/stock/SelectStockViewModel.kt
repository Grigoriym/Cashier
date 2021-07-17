package com.grappim.cashier.ui.selectinfo.stock

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.data.workers.WorkerHelper
import com.grappim.cashier.domain.outlet.GetOutletsUseCase
import com.grappim.cashier.domain.outlet.SaveStockInfoUseCase
import com.grappim.cashier.domain.outlet.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectStockViewModel @Inject constructor(
  private val getOutletsUseCase: GetOutletsUseCase,
  private val saveStockInfoUseCase: SaveStockInfoUseCase,
  private val workerHelper: WorkerHelper
) : ViewModel() {

  private val _stocks = MutableStateFlow<Resource<List<Stock>>>(Resource.Loading)
  val stocks: StateFlow<Resource<List<Stock>>>
    get() = _stocks

  val stockProgresses: List<StockProgressItem> = getStockProgressItems()

  init {
    getStocks()
  }

  @MainThread
  fun getStocks() {
    viewModelScope.launch {
      getOutletsUseCase()
        .onStart {
          _stocks.value = Resource.Loading
        }
        .catch { throwable: Throwable ->
          _stocks.value = Resource.Error(throwable)
        }
        .collect {
          _stocks.value = Resource.Success(it)
        }
    }
  }

  @MainThread
  fun saveStock(stock: Stock) {
    viewModelScope.launch {
      saveStockInfoUseCase.invoke(stock)
//            workerHelper.startMainWorkers()
    }
  }

  private fun getStockProgressItems(): List<StockProgressItem> =
    listOf(
      StockProgressItem(R.string.outlet_selecting, true),
      StockProgressItem(R.string.outlet_checkout, false),
      StockProgressItem(R.string.title_empty, false)
    )
}