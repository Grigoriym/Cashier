package com.grappim.cashier.domain.cashier

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.repository.SelectInfoRepository
import javax.inject.Inject

class GetCashBoxesUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CashBox>> =
        selectInfoRepository.getCashBoxes()
}