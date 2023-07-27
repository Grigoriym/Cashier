package com.grappim.cashier.di.rootactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.grappim.cashier.di.navigation.ActivityNavigationModule
import com.grappim.common.di.ActivityContext
import com.grappim.common.di.ActivityFragmentManager
import com.grappim.common.di.ActivityScope
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.BindsInstance
import dagger.Component

@[ActivityScope Component(
    modules = [
        RootActivityBindsModule::class,
        RootActivityDepsModule::class,
        ActivityNavigationModule::class
    ],
    dependencies = [
        RootActivityDeps::class
    ]
)]
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
