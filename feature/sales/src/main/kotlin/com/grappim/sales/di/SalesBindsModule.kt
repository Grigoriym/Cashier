package com.grappim.sales.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.sales.ui.SalesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SalesBindsModule {

    @[Binds IntoMap ViewModelKey(SalesViewModel::class)]
    fun provideAuthViewModel(salesViewModel: SalesViewModel): ViewModel

}