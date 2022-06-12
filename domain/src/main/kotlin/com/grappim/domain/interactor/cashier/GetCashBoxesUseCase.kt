package com.grappim.domain.interactor.cashier

import com.grappim.common.lce.Try
import com.grappim.domain.model.cashbox.CashBox

interface GetCashBoxesUseCase {
    suspend fun execute(): Try<List<CashBox>, Throwable>
}