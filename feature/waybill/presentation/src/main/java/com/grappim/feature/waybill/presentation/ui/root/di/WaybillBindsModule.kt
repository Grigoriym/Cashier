package com.grappim.feature.waybill.presentation.ui.root.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel.WaybillRootViewModel
import com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel.WaybillRootViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillRootViewModel::class)]
    fun provideAuthViewModel(waybillRootViewModel: WaybillRootViewModelImpl): ViewModel
}
