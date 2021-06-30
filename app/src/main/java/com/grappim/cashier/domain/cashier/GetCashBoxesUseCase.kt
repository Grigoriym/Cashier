package com.grappim.cashier.domain.cashier

import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCashBoxesUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository
) {

    operator fun invoke(): Flow<List<CashBox>> =
        selectInfoRepository.getCashBoxes()
}