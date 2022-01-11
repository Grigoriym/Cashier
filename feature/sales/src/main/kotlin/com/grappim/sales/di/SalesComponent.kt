package com.grappim.sales.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.sales.ui.SalesFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        CoroutinesModule::class,
        SalesBindsModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        SalesDeps::class
    ]
)]
internal interface SalesComponent {

    fun inject(salesFragment: SalesFragment)
}
