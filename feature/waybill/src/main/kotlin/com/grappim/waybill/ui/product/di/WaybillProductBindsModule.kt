package com.grappim.waybill.ui.product.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.product.ui.WaybillProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillProductBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillProductViewModel::class)]
    fun bindWaybillListViewModel(waybillProductViewModel: WaybillProductViewModel): ViewModel


}