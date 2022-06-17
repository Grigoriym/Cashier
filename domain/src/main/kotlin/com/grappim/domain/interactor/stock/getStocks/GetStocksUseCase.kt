package com.grappim.domain.interactor.stock.getStocks

import com.grappim.common.lce.Try
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import javax.inject.Inject

class GetStocksUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository,
    private val selectStockLocalRepository: SelectStockLocalRepository,
) {

    suspend fun execute(): Try<List<Stock>, Throwable> {
        val result = selectInfoRemoteRepository.getStocks2()
        selectStockLocalRepository.setStocks(result)
        return Try.Success(result)
    }

}