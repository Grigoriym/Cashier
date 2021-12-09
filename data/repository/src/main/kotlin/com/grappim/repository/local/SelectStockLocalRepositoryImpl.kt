package com.grappim.repository.local

import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.local.SelectStockLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectStockLocalRepositoryImpl @Inject constructor(

) : SelectStockLocalRepository {

    private val stocks = mutableListOf<Stock>()
    private var selectedStockPosition: Int = -1

    private val _stocksFlow = MutableStateFlow(stocks)
    override val stocksFlow: Flow<List<Stock>>
        get() = _stocksFlow

    override fun setStocks(list: List<Stock>) {
        stocks.clear()
        stocks.addAll(list)
        _stocksFlow.value = stocks
    }

    override fun setSelectedStock(stock: Stock) {
        selectedStockPosition = if (selectedStockPosition == -1 ||
            selectedStockPosition != stocks.indexOf(stock)
        ) {
            stocks.indexOf(stock)
        } else {
            -1
        }
    }

    override fun getSelectedStock(): Stock? =
        stocks.getOrNull(selectedStockPosition)

    override fun getStocks(): List<Stock> =
        stocks.toList()

    override fun clear() {
        _stocksFlow.value.clear()
        stocks.clear()
        selectedStockPosition = -1
    }
}