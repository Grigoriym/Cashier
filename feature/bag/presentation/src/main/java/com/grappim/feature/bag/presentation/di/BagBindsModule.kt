package com.grappim.feature.bag.presentation.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.feature.bag.presentation.ui.viewmodel.BagViewModel
import com.grappim.feature.bag.presentation.ui.viewmodel.BagViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BagBindsModule {

    @[Binds IntoMap ViewModelKey(BagViewModel::class)]
    fun provideAuthViewModel(bagViewModel: BagViewModelImpl): ViewModel
}
