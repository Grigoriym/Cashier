package com.grappim.cashier.di.root_activity

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.navigation.router.AppRouterImpl
import com.grappim.cashier.core.navigation.screens.CashierScreensImpl
import com.grappim.cashier.ui.root.MainViewModelImpl
import com.grappim.core.MainViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityBindsModule {

    @[Binds IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModelImpl): ViewModel

    @Binds
    fun bindAppCiceroneHolder(
        appCiceroneHolderImpl: AppRouterImpl
    ): AppRouter

    @Binds
    fun bindCashierScreens(
        cashierScreensImpl: CashierScreensImpl
    ): CashierScreens

}