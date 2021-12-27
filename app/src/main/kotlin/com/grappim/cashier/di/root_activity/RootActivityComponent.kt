package com.grappim.cashier.di.root_activity

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.grappim.auth.di.AuthDeps
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.ui.root.MainActivity
import com.grappim.di.ActivityContext
import com.grappim.di.ActivityScope
import com.grappim.menu.di.MenuDeps
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
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
            rootActivityDeps: RootActivityDeps
        ): RootActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}