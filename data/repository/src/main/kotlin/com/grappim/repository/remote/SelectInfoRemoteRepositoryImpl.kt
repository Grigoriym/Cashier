package com.grappim.repository.remote

import com.grappim.common.asynchronous.di.ApplicationScope
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.mappers.cashbox.toDomain
import com.grappim.network.mappers.stock.toDomain
import com.grappim.network.model.cashbox.GetCashBoxListRequestDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScope
class SelectInfoRemoteRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val generalStorage: GeneralStorage
) : SelectInfoRemoteRepository {

    override suspend fun saveCashBox(
        params: SaveCashBoxUseCase.Params
    ) = applicationScope.launch {
        generalStorage.setCashBoxInfo(params.cashBox)
    }.join()

    override suspend fun saveStock(
        params: SaveStockInfoUseCase.Params
    ) = applicationScope.launch {
        generalStorage.setStockInfo(params.stock)
    }.join()

    override fun getStocks(): Flow<Try<List<Stock>>> = flow {
        emit(Try.Loading)
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        val mappedResponse = response.stocks.toDomain()
        emit(Try.Success(mappedResponse))
    }

    override suspend fun getStocks2(): List<Stock> {
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        return response.stocks.toDomain()
    }

    override fun getCashBoxes(): Flow<Try<List<CashBox>>> =
        flow {
            emit(Try.Loading)
            val response = cashierApi.getCashBoxList(
                getCashBoxListRequestDTO = GetCashBoxListRequestDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.stockId
                )
            )
            val domain = response.cashBoxes.toDomain()
            emit(Try.Success(domain))
        }
}