package com.grappim.core.di.root_activity

import androidx.fragment.app.FragmentManager
import com.grappim.core.di.components_deps.navigation.*
import com.grappim.core.ui.MainActivity
import com.grappim.di.ActivityScope
import dagger.BindsInstance
import dagger.Component

@[ActivityScope Component(
    modules = [
        RootActivityBindsModule::class,
        RootActivityNavigationBindsModule::class,
        RootActivityDepsModule::class,
        RootActivityModule::class
    ],
    dependencies = [
        RootActivityDeps::class
    ]
)]
interface RootActivityComponent :
    NavigationDeps,
    AuthNavigationDeps,
    SignUpNavigationDeps,
    SelectStockNavigationDeps,
    SelectCashBoxNavigationDeps {

    @Component.Builder
    interface Builder {
        fun deps(rootActivityDeps: RootActivityDeps): Builder

        @BindsInstance
        fun bindFragmentManager(fragmentManager: FragmentManager): Builder

        fun build(): RootActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}