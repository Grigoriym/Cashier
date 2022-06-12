package com.grappim.domain.interactor.cashier

import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class SaveCashBoxUseCaseImpl @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) : SaveCashBoxUseCase {

    override suspend fun execute(parameters: SaveCashBoxParams) =
        selectInfoRemoteRepository.saveCashBox(parameters)
}