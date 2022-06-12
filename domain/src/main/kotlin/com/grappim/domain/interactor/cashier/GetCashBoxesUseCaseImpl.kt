package com.grappim.domain.interactor.cashier

import com.grappim.common.lce.Try
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class GetCashBoxesUseCaseImpl @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) : GetCashBoxesUseCase {

    override suspend fun execute(): Try<List<CashBox>, Throwable> =
        selectInfoRemoteRepository.getCashBoxes()

}