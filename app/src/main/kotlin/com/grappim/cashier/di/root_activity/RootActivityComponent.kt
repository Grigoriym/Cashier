package com.grappim.cashier.di.root_activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.grappim.cashier.ui.root.MainActivity
import com.grappim.cashier.ui.splash.SplashFragment
import com.grappim.common.di.ActivityContext
import com.grappim.common.di.ActivityScope
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
interface RootActivityComponent : FeatureDeps {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @ActivityContext context: Context,
            @BindsInstance fragmentManager: FragmentManager,
            @BindsInstance activity: AppCompatActivity,
            rootActivityDeps: RootActivityDeps
        ): RootActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(splashFragment: SplashFragment)
}