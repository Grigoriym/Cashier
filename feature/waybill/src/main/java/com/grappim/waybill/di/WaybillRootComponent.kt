package com.grappim.waybill.di

import com.grappim.date_time.DateTimeModule
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.waybill.ui.list.di.WaybillListDeps
import com.grappim.waybill.ui.root.WaybillRootFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        WaybillBindsModule::class,
        WaybillRootDepsModule::class,
        CoroutinesModule::class,
        DateTimeModule::class
    ],
    dependencies = [
        WaybillRootDeps::class
    ]
)]
interface WaybillRootComponent :
    WaybillListDeps {

    @Component.Factory
    interface Factory {
        fun create(
            waybillRootDeps: WaybillRootDeps
        ): WaybillRootComponent
    }

    fun inject(waybillRootFragment: WaybillRootFragment)

}