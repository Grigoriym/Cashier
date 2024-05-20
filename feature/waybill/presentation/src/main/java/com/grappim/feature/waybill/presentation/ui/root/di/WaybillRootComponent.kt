package com.grappim.feature.waybill.presentation.ui.root.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.datetime.DateTimeModule
import com.grappim.feature.waybill.presentation.ui.details.di.WaybillDetailsDeps
import com.grappim.feature.waybill.presentation.ui.list.di.WaybillListDeps
import com.grappim.feature.waybill.presentation.ui.product.di.WaybillProductDeps
import com.grappim.feature.waybill.presentation.ui.scanner.di.WaybillScannerDeps
import com.grappim.feature.waybill.presentation.ui.search.di.SearchWaybillProductDeps
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        WaybillBindsModule::class,
        WaybillRootDepsModule::class,
        CoroutinesModule::class,
        DateTimeModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        WaybillRootDeps::class
    ]
)
]
interface WaybillRootComponent :
    WaybillListDeps,
    WaybillDetailsDeps,
    WaybillProductDeps,
    SearchWaybillProductDeps,
    WaybillScannerDeps {

    @Component.Factory
    interface Factory {
        fun create(
            waybillRootDeps: WaybillRootDeps,
            @[BindsInstance FeatureFragmentManager] fragmentManager: FragmentManager
        ): WaybillRootComponent
    }

    fun deps(): ComponentDependenciesProvider
    fun multiViewModelFactory(): MultiViewModelFactory
}
