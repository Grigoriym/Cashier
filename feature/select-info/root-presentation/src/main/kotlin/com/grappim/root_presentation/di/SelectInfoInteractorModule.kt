package com.grappim.root_presentation.di

import com.grappim.domain.interactor.cashier.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashier.GetCashBoxesUseCaseImpl
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCaseImpl
import com.grappim.domain.interactor.outlet.GetStocksUseCase
import com.grappim.domain.interactor.outlet.GetStocksUseCaseImpl
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCaseImpl
import com.grappim.domain.interactor.outlet.SaveStockUseCase
import dagger.Binds
import dagger.Module

@Module
interface SelectInfoInteractorModule {
    @Binds
    fun bindGetStocksUseCase(
        getStocksUseCaseImpl: GetStocksUseCaseImpl
    ): GetStocksUseCase

    @Binds
    fun bindSaveStockUseCase(
        saveStocksUseCaseImpl: SaveStockInfoUseCaseImpl
    ): SaveStockUseCase

    @Binds
    fun bindGetCashBoxesUseCase(
        getCashBoxesUseCaseImpl: GetCashBoxesUseCaseImpl
    ): GetCashBoxesUseCase

    @Binds
    fun bindSaveCashBoxUseCase(
        saveCashBoxUseCaseImpl: SaveCashBoxUseCaseImpl
    ): SaveCashBoxUseCase
}