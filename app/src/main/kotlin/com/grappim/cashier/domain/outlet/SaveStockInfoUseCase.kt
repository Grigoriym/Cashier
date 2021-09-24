package com.grappim.cashier.domain.outlet

import com.grappim.cashier.domain.repository.SelectInfoRepository
import javax.inject.Inject

class SaveStockInfoUseCase @Inject constructor(
    private val selectInfoRepository: SelectInfoRepository
) {

    suspend operator fun invoke(stock: Stock) = selectInfoRepository.saveStock(stock)
}