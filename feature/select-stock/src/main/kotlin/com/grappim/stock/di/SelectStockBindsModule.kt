package com.grappim.stock.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.stock.ui.viewmodel.SelectStockViewModel
import com.grappim.stock.ui.viewmodel.SelectStockViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectStockBindsModule {

    @[Binds IntoMap ViewModelKey(SelectStockViewModel::class)]
    fun provideAuthViewModel(
        selectStockViewModelImpl: SelectStockViewModelImpl
    ): ViewModel

}