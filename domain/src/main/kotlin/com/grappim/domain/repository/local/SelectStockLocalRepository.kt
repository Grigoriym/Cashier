package com.grappim.domain.repository.local

import com.grappim.domain.model.outlet.Stock
import kotlinx.coroutines.flow.Flow

interface SelectStockLocalRepository {

    val stocksFlow: Flow<List<Stock>>

    fun setStocks(list: List<Stock>)

    fun getStocks(): List<Stock>

    fun setSelectedStock(stock: Stock)

    fun getSelectedStock(): Stock?

    fun clear()
}