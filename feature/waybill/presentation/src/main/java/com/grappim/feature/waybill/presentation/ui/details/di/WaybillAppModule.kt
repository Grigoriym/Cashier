package com.grappim.feature.waybill.presentation.ui.details.di

import com.grappim.feature.waybill.network.di.WaybillApiModule
import com.grappim.feature.waybill.repository.di.WaybillRepositoryBindsModule
import dagger.Module

@Module(
    includes = [
        WaybillApiModule::class,
        WaybillRepositoryBindsModule::class
    ]
)
interface WaybillAppModule
