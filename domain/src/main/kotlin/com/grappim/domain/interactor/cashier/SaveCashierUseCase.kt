package com.grappim.domain.interactor.cashier

import com.grappim.domain.base.NoParams
import com.grappim.domain.base.CoroutineUseCase
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.repository.SelectInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveCashierUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<SaveCashierUseCase.Params, NoParams>(ioDispatcher) {

    data class Params(
        val cashBox: CashBox
    )

    override suspend fun execute(parameters: Params): NoParams =
        selectInfoRepository.saveCashBox(parameters)
}