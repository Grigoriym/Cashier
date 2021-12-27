package com.grappim.stock.di

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
        SelectStockDeps::class
    ]
)]
interface SelectStockComponent {

    fun inject(selectStockFragment: SelectStockFragment)

}