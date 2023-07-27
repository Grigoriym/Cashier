package com.grappim.feature.waybill.presentation.ui.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.feature.waybill.presentation.ui.details.di.WaybillDetailsDeps
import com.grappim.feature.waybill.presentation.ui.list.di.WaybillListDeps
import com.grappim.feature.waybill.presentation.ui.product.di.WaybillProductDeps
import com.grappim.feature.waybill.presentation.ui.scanner.di.WaybillScannerDeps
import com.grappim.feature.waybill.presentation.ui.search.di.SearchWaybillProductDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillRootDepsModule {

    @[Binds IntoMap ComponentDepsKey(WaybillListDeps::class)]
    fun bindWaybillListDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(WaybillDetailsDeps::class)]
    fun bindWaybillDetailsDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(WaybillProductDeps::class)]
    fun bindWaybillProductDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SearchWaybillProductDeps::class)]
    fun bindSearchWaybillProductDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(WaybillScannerDeps::class)]
    fun bindSearchWaybillWaybillScannerDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps
}
