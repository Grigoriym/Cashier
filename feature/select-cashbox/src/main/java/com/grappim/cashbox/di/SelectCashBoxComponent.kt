package com.grappim.cashbox.di

import com.grappim.cashbox.ui.SelectCashBoxFragment
import com.grappim.core.di.components_deps.navigation.NavigationDeps
import com.grappim.core.di.components_deps.navigation.SelectCashBoxNavigationDeps
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import dagger.Component

@[FeatureScope Component(
    modules = [
        SelectCashBoxBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SelectCashBoxDeps::class,
        SelectCashBoxNavigationDeps::class
    ]
)]
interface SelectCashBoxComponent {

    @Component.Builder
    interface Builder {
        fun deps(selectCashBoxDeps: SelectCashBoxDeps): Builder
        fun navDeps(selectCashBoxNavigationDeps: SelectCashBoxNavigationDeps): Builder
        fun build(): SelectCashBoxComponent
    }

    fun inject(selectCashBoxFragment: SelectCashBoxFragment)
}