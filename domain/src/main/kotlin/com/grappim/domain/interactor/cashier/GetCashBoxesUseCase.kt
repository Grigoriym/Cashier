package com.grappim.domain.interactor.cashier

import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Result
import com.grappim.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCashBoxesUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, List<CashBox>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Result<List<CashBox>>> =
        selectInfoRepository.getCashBoxes()

}