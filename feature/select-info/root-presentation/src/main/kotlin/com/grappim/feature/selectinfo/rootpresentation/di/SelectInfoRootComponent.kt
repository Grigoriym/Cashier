package com.grappim.feature.selectinfo.rootpresentation.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.stock.di.SelectStockDeps
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        SelectInfoRootBindsModule::class,
        SelectIntoRootDepsModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SelectInfoRootDeps::class
    ]
)
]
interface SelectInfoRootComponent :
    SelectStockDeps,
    SelectCashBoxDeps {

    @Component.Factory
    interface Factory {
        fun create(
            selectInfoRootDeps: SelectInfoRootDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): SelectInfoRootComponent
    }

    fun multiViewModelFactory(): MultiViewModelFactory
    fun deps(): ComponentDependenciesProvider
}
