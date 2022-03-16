package com.grappim.waybill.ui.details.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.details.ui.viewmodel.WaybillDetailsViewModel
import com.grappim.waybill.ui.details.ui.viewmodel.WaybillDetailsViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillDetailsBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillDetailsViewModel::class)]
    fun bindWaybillDetailsViewModel(
        waybillDetailsViewModel: WaybillDetailsViewModelImpl
    ): ViewModel

}