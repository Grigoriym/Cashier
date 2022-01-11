package com.grappim.cashier.di.root_activity

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.cashier.ui.root.MainViewModelImpl
import com.grappim.cashier.ui.splash.SplashViewModel
import com.grappim.core.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityBindsModule {

    @[Binds IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModelImpl): ViewModel

    @[Binds IntoMap ViewModelKey(SplashViewModel::class)]
    fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel

}