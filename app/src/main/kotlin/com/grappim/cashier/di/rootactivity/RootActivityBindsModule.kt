package com.grappim.cashier.di.rootactivity

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.navigation.router.ActivityRouterImpl
import com.grappim.cashier.core.navigation.screens.CashierScreensImpl
import com.grappim.cashier.ui.root.MainViewModelImpl
import com.grappim.core.MainViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityBindsModule {

    @[Binds IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModelImpl): ViewModel

    @Binds
    fun bindAppCiceroneHolder(
        activityCiceroneHolderImpl: ActivityRouterImpl
    ): ActivityRouter

    @Binds
    fun bindCashierScreens(
        cashierScreensImpl: CashierScreensImpl
    ): CashierScreens

}
