package com.grappim.domain.repository

import com.grappim.domain.base.Try
import com.grappim.domain.interactor.cashier.SaveCashierUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import kotlinx.coroutines.flow.Flow

interface SelectInfoRepository {
    suspend fun saveCashBox(
        params: SaveCashierUseCase.Params
    )

    suspend fun saveStock(params: SaveStockInfoUseCase.Params)

    fun getCashBoxes(): Flow<Try<List<CashBox>>>

    fun getStocks(): Flow<Try<List<Stock>>>
}