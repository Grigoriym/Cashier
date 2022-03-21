package com.grappim.stock.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.domain.model.outlet.Stock
import com.grappim.stock.model.StockProgressItem
import kotlinx.coroutines.flow.StateFlow

abstract class SelectStockViewModel : BaseViewModel() {

    abstract val selectedStock: StateFlow<Stock?>
    abstract val stocksResult: StateFlow<List<Stock>>
    abstract val stockProgresses: List<StockProgressItem>

    abstract fun selectStock(stock: Stock)
    abstract fun saveStock()
    abstract fun getStocks()

}