package com.grappim.stock.di

import com.grappim.core.di.components_deps.navigation.SelectStockNavigationDeps
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.stock.ui.SelectStockFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        CoroutinesModule::class,
        SelectStockBindsModule::class
    ],
    dependencies = [
        SelectStockDeps::class,
        SelectStockNavigationDeps::class
    ]
)]
interface SelectStockComponent {

    @Component.Builder
    interface Builder {
        fun deps(selectStockDeps: SelectStockDeps): Builder
        fun navDeps(selectStockNavigationDeps: SelectStockNavigationDeps): Builder
        fun build(): SelectStockComponent
    }

    fun inject(selectStockFragment: SelectStockFragment)

}