package com.grappim.cashier.domain.outlet

import com.grappim.cashier.domain.repository.SelectInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOutletsUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository
) {

    operator fun invoke(): Flow<List<Stock>> =
        selectInfoRepository.getStocks()
}