package com.grappim.waybill.ui.root.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.root.ui.viewmodel.WaybillRootViewModel
import com.grappim.waybill.ui.root.ui.viewmodel.WaybillRootViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillRootViewModel::class)]
    fun provideAuthViewModel(waybillRootViewModel: WaybillRootViewModelImpl): ViewModel
}