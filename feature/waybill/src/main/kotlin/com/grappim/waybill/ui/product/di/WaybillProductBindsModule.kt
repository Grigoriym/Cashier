package com.grappim.waybill.ui.product.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.product.ui.viewmodel.WaybillProductViewModel
import com.grappim.waybill.ui.product.ui.viewmodel.WaybillProductViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillProductBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillProductViewModel::class)]
    fun bindWaybillListViewModel(waybillProductViewModel: WaybillProductViewModelImpl): ViewModel


}