package com.grappim.domain.interactor.cashier

import com.grappim.domain.base.NoParams
import com.grappim.domain.base.CoroutineUseCase
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.repository.SelectInfoRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveCashBoxUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<SaveCashBoxUseCase.Params, NoParams>(ioDispatcher) {

    data class Params(
        val cashBox: CashBox
    )

    override suspend fun execute(parameters: Params): NoParams =
        selectInfoRemoteRepository.saveCashBox(parameters)
}