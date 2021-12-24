package com.grappim.stock.di

import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.stock.SelectStockFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        CoroutinesModule::class,
        SelectStockBindsModule::class
    ],
    dependencies = [
        SelectStockDeps::class
    ]
)]
interface SelectStockComponent {

    @Component.Builder
    interface Builder {
        fun deps(selectStockDeps: SelectStockDeps): Builder
        fun build(): SelectStockComponent
    }

    fun inject(selectStockFragment: SelectStockFragment)

}