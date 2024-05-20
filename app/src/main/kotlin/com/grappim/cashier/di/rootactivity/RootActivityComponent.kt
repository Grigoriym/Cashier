package com.grappim.cashier.di.rootactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.di.ActivityContext
import com.grappim.cashier.common.di.ActivityFragmentManager
import com.grappim.cashier.common.di.ActivityScope
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.di.navigation.ActivityNavigationModule
import dagger.BindsInstance
import dagger.Component

@[
ActivityScope Component(
    modules = [
        RootActivityBindsModule::class,
        RootActivityDepsModule::class,
        ActivityNavigationModule::class
    ],
    dependencies = [
        RootActivityDeps::class
    ]
)
]
interface RootActivityComponent : FeatureDeps {

    @Component.Factory
    interface Factory {
        fun create(
            @[BindsInstance ActivityContext] context: Context,
            @[BindsInstance ActivityFragmentManager] fragmentManager: FragmentManager,
            @BindsInstance activity: AppCompatActivity,
            rootActivityDeps: RootActivityDeps
        ): RootActivityComponent
    }

    fun deps(): ComponentDependenciesProvider
    fun multiViewModelFactory(): MultiViewModelFactory
}
