package com.grappim.cashier.domain.cashier

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.platform.FlowUseCase
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.extension.WithoutParams
import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCashBoxesUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<WithoutParams, List<CashBox>>(ioDispatcher) {

    override fun execute(parameters: WithoutParams): Flow<Resource<List<CashBox>>> =
        selectInfoRepository.getCashBoxes()

}