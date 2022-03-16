package com.grappim.cashier.di.root_activity

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.navigation.screens.CashierScreensImpl
import com.grappim.cashier.ui.root.MainViewModelImpl
import com.grappim.cashier.ui.splash.SplashViewModel
import com.grappim.navigation.CashierScreens
import com.grappim.core.MainViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.navigation.AppRouter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityBindsModule {

    @[Binds IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModelImpl): ViewModel

    @[Binds IntoMap ViewModelKey(SplashViewModel::class)]
    fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    fun bindAppCiceroneHolder(
        appCiceroneHolderImpl: com.grappim.cashier.core.navigation.router.AppRouterImpl
    ): AppRouter

    @Binds
    fun bindCashierScreens(
        cashierScreensImpl: CashierScreensImpl
    ): CashierScreens

}