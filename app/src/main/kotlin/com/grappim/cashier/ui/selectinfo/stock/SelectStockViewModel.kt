package com.grappim.cashier.ui.selectinfo.stock

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.domain.StockProgressItem
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.outlet.GetOutletsUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.outlet.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
    var error by mutableStateOf<Throwable?>(null)

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
            saveStockInfoUseCase.invoke(SaveStockInfoUseCase.Params(stockToSave))
        }
    }

    @MainThread
    fun getStocks() {
        viewModelScope.launch {
            getOutletsUseCase(withoutParams())
                .collect {
                    loading = it is Result.Loading

                    when (it) {
                        is Result.Success -> {
                            stocks.clear()
                            stocks.addAll(it.data)
                        }
                        is Result.Error -> {
                            error = it.exception
                        }
                    }
                }
        }
    }

    @MainThread
    fun saveStock(stock: Stock) {
        viewModelScope.launch {
            saveStockInfoUseCase.invoke(SaveStockInfoUseCase.Params(stock))
        }
    }

    private fun getStockProgressItems(): List<StockProgressItem> =
        listOf(
            StockProgressItem(R.string.outlet_selecting, true),
            StockProgressItem(R.string.outlet_checkout, false)
        )
}