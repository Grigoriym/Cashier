package com.grappim.domain.interactor.cashbox.saveCashbox

import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class SaveCashBoxUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) {

    suspend fun execute(parameters: SaveCashBoxParams) =
        selectInfoRemoteRepository.saveCashBox(parameters)
}
