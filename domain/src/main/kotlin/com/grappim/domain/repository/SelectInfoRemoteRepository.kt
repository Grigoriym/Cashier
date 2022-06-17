package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.cashbox.saveCashbox.SaveCashBoxParams
import com.grappim.domain.interactor.stock.saveStock.SaveStockParams
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock

interface SelectInfoRemoteRepository {

    suspend fun saveCashBox(
        params: SaveCashBoxParams
    )

    suspend fun saveStock(params: SaveStockParams)

    suspend fun getCashBoxes(): Try<List<CashBox>, Throwable>

    suspend fun getStocks2(): List<Stock>

}