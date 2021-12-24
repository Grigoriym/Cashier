package com.grappim.cashbox.di

import com.grappim.cashbox.SelectCashBoxFragment
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import dagger.Component

@[FeatureScope Component(
    modules = [
        SelectCashBoxBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SelectCashBoxDeps::class
    ]
)]
interface SelectCashBoxComponent {

    @Component.Builder
    interface Builder {
        fun deps(selectCashBoxDeps: SelectCashBoxDeps): Builder
        fun build(): SelectCashBoxComponent
    }

    fun inject(selectCashBoxFragment: SelectCashBoxFragment)
}