package com.grappim.cashier.ui.selectinfo.stock

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.domain.outlet.GetOutletsUseCase
import com.grappim.cashier.domain.outlet.SaveStockInfoUseCase
import com.grappim.cashier.domain.outlet.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectStockViewModel @Inject constructor(
    private val getOutletsUseCase: GetOutletsUseCase,
    private val saveStockInfoUseCase: SaveStockInfoUseCase
) : ViewModel() {

    val stocks = mutableStateListOf<Stock>()

    val stockProgresses: List<StockProgressItem> = getStockProgressItems()

    private var selectedStockPosition by mutableStateOf(-1)
    val selectedStock: Stock?
        get() = stocks.getOrNull(selectedStockPosition)

    var loading by mutableStateOf(false)

    init {
        getStocks()
    }

    fun selectStock(stock: Stock) {
        selectedStockPosition = if (selectedStockPosition == -1 ||
            selectedStockPosition != stocks.indexOf(stock)
        ) {
            stocks.indexOf(stock)
        } else {
            -1
        }
    }

    fun saveStock() {
        viewModelScope.launch {
            val stockToSave = requireNotNull(selectedStock) {
                "Stock must not be null"
            }
            saveStockInfoUseCase.invoke(stockToSave)
        }
    }

    @MainThread
    fun getStocks() {
        viewModelScope.launch {
            getOutletsUseCase()
                .onStart {
                    loading = true
                }
                .onCompletion {
                    loading = false
                }
                .catch { throwable: Throwable ->
//                    _stocks.value = Resource.Error(throwable)
                }
                .collect {
                    stocks.clear()
                    stocks.addAll(it)
                }
        }
    }

    @MainThread
    fun saveStock(stock: Stock) {
        viewModelScope.launch {
            saveStockInfoUseCase.invoke(stock)
        }
    }

    private fun getStockProgressItems(): List<StockProgressItem> =
        listOf(
            StockProgressItem(R.string.outlet_selecting, true),
            StockProgressItem(R.string.outlet_checkout, false)
        )
}