package com.grappim.waybill.ui.root.di

import com.grappim.domain.interactor.waybill.*
import dagger.Binds
import dagger.Module

@Module
interface WaybillInteractorModule {
    @Binds
    fun bindConductWaybillUseCase(
        conductWaybillUseCaseImpl: ConductWaybillUseCaseImpl
    ): ConductWaybillUseCase

    @Binds
    fun bindCreateWaybillProductUseCase(
        createWaybillProductUseCaseImpl: CreateWaybillProductUseCaseImpl
    ): CreateWaybillProductUseCase

    @Binds
    fun bindGetWaybillProductByBarcodeUseCase(
        getWaybillProductByBarcodeUseCaseImpl: GetWaybillProductByBarcodeUseCaseImpl
    ): GetWaybillProductByBarcodeUseCase

    @Binds
    fun bindGetWaybillProductsUseCase(
        getWaybillProductsUseCaseImpl: GetWaybillProductsUseCaseImpl
    ): GetWaybillProductsUseCase

    @Binds
    fun bindRollbackWaybillUseCase(
        rollbackWaybillUseCaseImpl: RollbackWaybillUseCaseImpl
    ): RollbackWaybillUseCase

    @Binds
    fun bindUpdateWaybillProductUseCase(
        updateWaybillProductUseCaseImpl: UpdateWaybillProductUseCaseImpl
    ): UpdateWaybillProductUseCase

    @Binds
    fun bindGetWaybillListPagingUseCase(
        getWaybillListPagingUseCaseImpl: GetWaybillListPagingUseCaseImpl
    ): GetWaybillListPagingUseCase

    @Binds
    fun bindCreateDraftWaybillUseCase(
        createDraftWaybillUseCaseImpl: CreateDraftWaybillUseCaseImpl
    ): CreateDraftWaybillUseCase
}