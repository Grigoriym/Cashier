package com.grappim.root_presentation.di

import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.root_presentation.ui.SelectInfoRootFragment
import com.grappim.stock.di.SelectStockDeps
import dagger.Component

@[FeatureScope Component(
    modules = [
        SelectInfoRootBindsModule::class,
        SelectIntoRootDepsModule::class

    ],
    dependencies = [
        SelectInfoRootDeps::class
    ]
)]
interface SelectInfoRootComponent :
    SelectStockDeps,
    SelectCashBoxDeps {

    @Component.Factory
    interface Factory {
        fun create(
            selectInfoRootDeps: SelectInfoRootDeps
        ): SelectInfoRootComponent
    }

//    fun inject(selectInfoRootFragment: SelectInfoRootFragment)

    fun multiViewModelFactory(): MultiViewModelFactory
    fun deps(): ComponentDependenciesProvider
}