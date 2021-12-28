package com.grappim.bag.di

import com.grappim.bag.ui.BagFragment
import com.grappim.calculations.DecimalFormatModule
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import dagger.Component

@[FeatureScope Component(
    modules = [
        BagBindsModules::class,
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