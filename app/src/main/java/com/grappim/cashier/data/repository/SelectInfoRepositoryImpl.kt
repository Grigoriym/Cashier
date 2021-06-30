package com.grappim.cashier.data.repository

import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.BaseRepository
import com.grappim.cashier.data.remote.model.cashbox.CashBoxMapper.toDomain
import com.grappim.cashier.data.remote.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.cashier.data.remote.model.outlet.OutletMapper.toDomain
import com.grappim.cashier.di.modules.ApplicationScope
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.di.modules.QualifierCashierApi
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.outlet.Stock
import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectInfoRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val generalStorage: GeneralStorage
) : BaseRepository(), SelectInfoRepository {

    override suspend fun saveCashBox(cashBox: CashBox) = withContext(ioDispatcher) {
        applicationScope.launch {
            generalStorage.setCashierInfo(cashBox)
        }.join()
    }

    override suspend fun saveStock(stock: Stock) = withContext(ioDispatcher) {
        applicationScope.launch {
            generalStorage.setStockInfo(stock)
        }.join()
    }

    override fun getStocks(): Flow<List<Stock>> = flow {
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        emit(response)
    }.map {
        it.stocks.toDomain()
    }.flowOn(ioDispatcher)

    override fun getCashBoxes(): Flow<List<CashBox>> =
        flow {
            val response = cashierApi.getCashBoxList(
                getCashBoxListRequestDTO = GetCashBoxListRequestDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId()
                )
            )
            emit(response)
        }.map {
            it.cashBoxes?.toDomain() ?: listOf()
        }.flowOn(ioDispatcher)

}