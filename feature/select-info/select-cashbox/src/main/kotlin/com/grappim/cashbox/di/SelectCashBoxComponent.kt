package com.grappim.cashbox.di

import com.grappim.cashbox.ui.view.SelectCashBoxFragment
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.common.di.FragmentScope
import dagger.Component

@[FragmentScope Component(
    modules = [
        SelectCashBoxBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SelectCashBoxDeps::class
    ]
)]
interface SelectCashBoxComponent {

    fun inject(selectCashBoxFragment: SelectCashBoxFragment)
}