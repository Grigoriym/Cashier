package com.grappim.repository.remote

import com.grappim.domain.base.Result
import com.grappim.domain.di.ApplicationScope
import com.grappim.domain.interactor.cashier.SaveCashierUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.CashierApi
import com.grappim.network.di.QualifierCashierApi
import com.grappim.network.mappers.cashbox.CashBoxMapper
import com.grappim.network.mappers.outlet.StockMapper
import com.grappim.network.model.cashbox.GetCashBoxListRequestDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectInfoRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val generalStorage: GeneralStorage,
    private val cashBoxMapper: CashBoxMapper,
    private val stockMapper: StockMapper
) : SelectInfoRepository {

    override suspend fun saveCashBox(
        params: SaveCashierUseCase.Params
    ) = applicationScope.launch {
        generalStorage.setCashierInfo(params.cashBox)
    }.join()

    override suspend fun saveStock(
        params: SaveStockInfoUseCase.Params
    ) = applicationScope.launch {
        generalStorage.setStockInfo(params.stock)
    }.join()

    override fun getStocks(): Flow<Result<List<Stock>>> = flow {
        emit(Result.Loading)
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        val mappedResponse = stockMapper.dtoToDomainList(response.stocks)
        emit(Result.Success(mappedResponse))
    }

    override fun getCashBoxes(): Flow<Result<List<CashBox>>> =
        flow {
            emit(Result.Loading)
            val response = cashierApi.getCashBoxList(
                getCashBoxListRequestDTO = GetCashBoxListRequestDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId()
                )
            )
            val domain = cashBoxMapper.dtoToDomainList(response.cashBoxes)
            emit(Result.Success(domain))
        }
}