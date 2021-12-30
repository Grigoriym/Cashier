package com.grappim.waybill.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.ComponentDepsKey
import com.grappim.waybill.ui.list.di.WaybillListDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillRootDepsModule {

    @[Binds IntoMap ComponentDepsKey(WaybillListDeps::class)]
    fun bindWaybillListDeps(
        waybillRootComponent: WaybillRootComponent
    ): ComponentDeps

}