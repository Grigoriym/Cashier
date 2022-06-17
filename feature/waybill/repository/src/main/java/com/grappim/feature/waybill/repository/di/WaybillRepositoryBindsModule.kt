package com.grappim.feature.waybill.repository.di

import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.feature.waybill.repository.local.WaybillLocalRepositoryImpl
import com.grappim.feature.waybill.repository.remote.WaybillRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface WaybillRepositoryBindsModule {
    @Binds
    fun bindWaybillLocalRepository(
        waybillLocalRepositoryImpl: WaybillLocalRepositoryImpl
    ): WaybillLocalRepository

    @Binds
    fun bindWaybillRepository(
        waybillRepositoryImpl: WaybillRepositoryImpl
    ): WaybillRepository
}