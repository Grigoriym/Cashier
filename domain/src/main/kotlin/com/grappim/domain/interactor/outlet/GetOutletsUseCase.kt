package com.grappim.domain.interactor.outlet

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOutletsUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, List<Stock>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Try<List<Stock>>> =
        selectInfoRepository.getStocks()

}