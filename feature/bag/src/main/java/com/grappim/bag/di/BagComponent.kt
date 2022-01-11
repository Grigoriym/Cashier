package com.grappim.bag.di

import com.grappim.bag.ui.BagFragment
import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import dagger.Component

@[FeatureScope Component(
    modules = [
        BagBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        BagDeps::class
    ]
)]
interface BagComponent {

    fun inject(bagFragment: BagFragment)

}