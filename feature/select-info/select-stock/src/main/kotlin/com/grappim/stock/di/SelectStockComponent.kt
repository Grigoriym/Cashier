package com.grappim.stock.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.common.di.FragmentScope
import com.grappim.stock.ui.view.SelectStockFragment
import dagger.Component

@[FragmentScope Component(
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