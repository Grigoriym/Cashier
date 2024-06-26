package com.grappim.menu.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.menu.ui.viewmodel.MenuViewModel
import com.grappim.menu.ui.viewmodel.MenuViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MenuBindsModule {

    @[Binds IntoMap ViewModelKey(MenuViewModel::class)]
    fun provideAuthViewModel(authViewModel: MenuViewModelImpl): ViewModel
}
