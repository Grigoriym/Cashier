package com.grappim.repository.remote

import com.grappim.common.asynchronous.di.ApplicationScope
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.cashbox.saveCashbox.SaveCashBoxParams
import com.grappim.domain.interactor.stock.saveStock.SaveStockParams
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.mappers.cashbox.toDomain
import com.grappim.network.mappers.stock.toDomain
import com.grappim.network.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.common.asynchronous.runOperationCatching
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScope
class SelectInfoRemoteRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val generalStorage: GeneralStorage
) : SelectInfoRemoteRepository {

    override suspend fun saveCashBox(
        params: SaveCashBoxParams
    ) = applicationScope.launch {
        generalStorage.setCashBoxInfo(params.cashBox)
    }.join()

    override suspend fun saveStock(
        params: SaveStockParams
    ) = applicationScope.launch {
        generalStorage.setStockInfo(params.stock)
    }.join()

    override suspend fun getStocks2(): List<Stock> {
        val response = cashierApi.getStocks(generalStorage.getMerchantId())
        return response.stocks.toDomain()
    }

    override suspend fun getCashBoxes(): Try<List<CashBox>, Throwable> =
        runOperationCatching {
            val response = cashierApi.getCashBoxList(
                getCashBoxListRequestDTO = GetCashBoxListRequestDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.stockId
                )
            )
            val domain = response.cashBoxes.toDomain()
            domain
        }
}