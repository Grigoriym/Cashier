package com.grappim.feature.selectinfo.rootpresentation.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationBindsModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.stock.di.SelectStockDeps
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        SelectInfoRootBindsModule::class,
        SelectIntoRootDepsModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SelectInfoRootDeps::class
    ]
)]
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
