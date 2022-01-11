package com.grappim.waybill.ui.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.waybill.ui.details.di.WaybillDetailsDeps
import com.grappim.waybill.ui.list.di.WaybillListDeps
import com.grappim.waybill.ui.product.di.WaybillProductDeps
import com.grappim.waybill.ui.search.di.SearchWaybillProductDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillRootDepsModule {

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(WaybillListDeps::class)]
    fun bindWaybillListDeps(
        waybillRootComponent: WaybillRootComponent
    ): com.grappim.common.di.deps.ComponentDeps

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(WaybillDetailsDeps::class)]
    fun bindWaybillDetailsDeps(
        waybillRootComponent: WaybillRootComponent
    ): com.grappim.common.di.deps.ComponentDeps

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(WaybillProductDeps::class)]
    fun bindWaybillProductDeps(
        waybillRootComponent: WaybillRootComponent
    ): com.grappim.common.di.deps.ComponentDeps

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(SearchWaybillProductDeps::class)]
    fun bindSearchWaybillProductDeps(
        waybillRootComponent: WaybillRootComponent
    ): com.grappim.common.di.deps.ComponentDeps
}