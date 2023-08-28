package com.grappim.feature.selectinfo.rootpresentation.di

import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.feature.selectinfo.rootpresentation.di.SelectInfoRootComponent
import com.grappim.stock.di.SelectStockDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectIntoRootDepsModule {

    @[Binds IntoMap ComponentDepsKey(SelectStockDeps::class)]
    fun bindSelectStockDeps(
        selectInfoRootComponent: SelectInfoRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectCashBoxDeps::class)]
    fun bindSelectCashBoxDeps(
        selectInfoRootComponent: SelectInfoRootComponent
    ): ComponentDeps
}
