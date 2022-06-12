package com.grappim.domain.interactor.outlet

import com.grappim.domain.repository.SelectInfoRemoteRepository
import javax.inject.Inject

class SaveStockInfoUseCaseImpl @Inject constructor(
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) : SaveStockUseCase {

    override suspend fun execute(parameters: SaveStockParams) =
        selectInfoRemoteRepository.saveStock(parameters)
}