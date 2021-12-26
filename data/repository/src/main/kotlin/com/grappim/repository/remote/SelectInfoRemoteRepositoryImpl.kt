package com.grappim.repository.remote

import com.grappim.di.AppScope
import com.grappim.domain.base.Try
import com.grappim.domain.di.ApplicationScope
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.mappers.cashbox.CashBoxMapper
import com.grappim.network.mappers.outlet.StockMapper
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
    private val generalStorage: GeneralStorage,
    private val cashBoxMapper: CashBoxMapper,
    private val stockMapper: StockMapper
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
        val mappedResponse = stockMapper.dtoToDomainList(response.stocks)
        emit(Try.Success(mappedResponse))
    }

    override suspend fun getStocks2(): List<Stock> {
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        return stockMapper.dtoToDomainList(response.stocks)
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
            val domain = cashBoxMapper.dtoToDomainList(response.cashBoxes)
            emit(Try.Success(domain))
        }
}