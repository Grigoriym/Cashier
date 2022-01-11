package com.grappim.bag.di

import androidx.lifecycle.ViewModel
import com.grappim.bag.ui.BagViewModel
import com.grappim.core.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BagBindsModule {

    @[Binds IntoMap ViewModelKey(BagViewModel::class)]
    fun provideAuthViewModel(bagViewModel: BagViewModel): ViewModel

}