package com.grappim.cashbox.di

import androidx.lifecycle.ViewModel
import com.grappim.cashbox.ui.viewmodel.SelectCashBoxViewModel
import com.grappim.cashbox.ui.viewmodel.SelectCashBoxViewModelImpl
import com.grappim.cashier.core.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectCashBoxBindsModule {

    @[Binds IntoMap ViewModelKey(SelectCashBoxViewModel::class)]
    fun provideSelectCashBoxViewModel(selectCashBoxViewModel: SelectCashBoxViewModelImpl): ViewModel
}
