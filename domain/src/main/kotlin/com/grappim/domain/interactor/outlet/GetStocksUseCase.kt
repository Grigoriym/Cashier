package com.grappim.domain.interactor.outlet

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.common.lce.Try
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStocksUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository,
    private val selectStockLocalRepository: SelectStockLocalRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, List<Stock>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Try<List<Stock>>> = flow {
        val result = selectInfoRemoteRepository.getStocks2()
        selectStockLocalRepository.setStocks(result)
        emit(Try.Success(result))
    }

}