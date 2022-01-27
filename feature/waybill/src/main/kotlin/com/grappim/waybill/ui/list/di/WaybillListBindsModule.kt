package com.grappim.waybill.ui.list.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.list.ui.viewmodel.WaybillListViewModel
import com.grappim.waybill.ui.list.ui.viewmodel.WaybillListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillListBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillListViewModel::class)]
    fun bindWaybillListViewModel(waybillListViewModel: WaybillListViewModelImpl): ViewModel

}