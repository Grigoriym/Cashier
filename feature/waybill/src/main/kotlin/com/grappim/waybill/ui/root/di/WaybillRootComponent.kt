package com.grappim.waybill.ui.root.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.date_time.DateTimeModule
import com.grappim.waybill.ui.details.di.WaybillDetailsDeps
import com.grappim.waybill.ui.list.di.WaybillListDeps
import com.grappim.waybill.ui.product.di.WaybillProductDeps
import com.grappim.waybill.ui.root.ui.WaybillRootFragment
import com.grappim.waybill.ui.search.di.SearchWaybillProductDeps
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
    WaybillListDeps,
    WaybillDetailsDeps,
    WaybillProductDeps,
    SearchWaybillProductDeps {

    @Component.Factory
    interface Factory {
        fun create(
            waybillRootDeps: WaybillRootDeps
        ): WaybillRootComponent
    }

    fun inject(waybillRootFragment: WaybillRootFragment)

}