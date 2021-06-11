package com.grappim.cashier.data.repository

import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.functional.map
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.BaseRepository
import com.grappim.cashier.data.remote.model.cashbox.CashBoxMapper.toDomain
import com.grappim.cashier.data.remote.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.cashier.domain.cashier.Cashier
import com.grappim.cashier.data.remote.model.outlet.OutletMapper.toDomain
import com.grappim.cashier.di.modules.QualifierCashierApi
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.outlet.Stock
import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectInfoRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseRepository(), SelectInfoRepository {

    override suspend fun saveCashBox(cashBox: CashBox) = withContext(coroutineContextProvider.io) {
        generalStorage.setCashierInfo(cashBox)
    }

    override suspend fun saveStock(stock: Stock) = withContext(coroutineContextProvider.io) {
        generalStorage.setStockInfo(stock)
    }

    override suspend fun getStocks(): Either<Throwable, List<Stock>> =
        apiCall {
            cashierApi.getStocks(generalStorage.getMerchantId())
        }.map {
            withContext(coroutineContextProvider.io) {
                it.stocks.toDomain()
            }
        }

    override suspend fun getCashBoxes(): Either<Throwable, List<CashBox>> =
        apiCall {
            cashierApi.getCashBoxList(
                getCashBoxListRequestDTO = GetCashBoxListRequestDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId()
                )
            )
        }.map {
            withContext(coroutineContextProvider.io) {
                it.cashBoxes?.toDomain() ?: listOf()
            }
        }

}