package com.grappim.domain.interactor.cashier

interface SaveCashBoxUseCase {
    suspend fun execute(parameters: SaveCashBoxParams)
}