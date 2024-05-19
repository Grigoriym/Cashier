package com.grappim.domain.interactor.stock.saveStock

import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class SaveStockUseCase @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) {

    suspend fun execute(parameters: SaveStockParams) =
        selectInfoRemoteRepository.saveStock(parameters)
}
