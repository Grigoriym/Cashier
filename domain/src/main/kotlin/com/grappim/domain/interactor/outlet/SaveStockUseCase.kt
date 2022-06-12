package com.grappim.domain.interactor.outlet

interface SaveStockUseCase {

    suspend fun execute(parameters: SaveStockParams)

}