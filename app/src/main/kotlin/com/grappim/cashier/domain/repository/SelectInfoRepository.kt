package com.grappim.cashier.domain.repository

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.outlet.Stock
import kotlinx.coroutines.flow.Flow

interface SelectInfoRepository {
    suspend fun saveCashBox(cashBox: CashBox)
    suspend fun saveStock(stock: Stock)

    fun getCashBoxes(): Flow<Resource<List<CashBox>>>

    fun getStocks(): Flow<Resource<List<Stock>>>
}