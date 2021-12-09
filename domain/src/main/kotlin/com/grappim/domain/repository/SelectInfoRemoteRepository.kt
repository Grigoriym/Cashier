package com.grappim.domain.repository

import com.grappim.domain.base.Try
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import kotlinx.coroutines.flow.Flow

interface SelectInfoRemoteRepository {

    suspend fun saveCashBox(
        params: SaveCashBoxUseCase.Params
    )

    suspend fun saveStock(params: SaveStockInfoUseCase.Params)

    fun getCashBoxes(): Flow<Try<List<CashBox>>>

    fun getStocks(): Flow<Try<List<Stock>>>

    suspend fun getStocks2(): List<Stock>

}