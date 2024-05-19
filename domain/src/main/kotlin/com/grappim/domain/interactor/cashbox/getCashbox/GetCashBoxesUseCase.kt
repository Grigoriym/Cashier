package com.grappim.domain.interactor.cashbox.getCashbox

import com.grappim.common.lce.Try
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class GetCashBoxesUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) {

    suspend fun execute(): Try<List<CashBox>, Throwable> =
        selectInfoRemoteRepository.getCashBoxes()

}
