package com.grappim.cashier.domain.outlet

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.platform.FlowUseCase
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.domain.extension.WithoutParams
import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOutletsUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<WithoutParams, List<Stock>>(ioDispatcher) {

    override fun execute(parameters: WithoutParams): Flow<Resource<List<Stock>>> =
        selectInfoRepository.getStocks()

}