package com.grappim.feature.waybill.presentation.ui.product.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.feature.waybill.presentation.ui.product.ui.viewmodel.WaybillProductViewModel
import com.grappim.feature.waybill.presentation.ui.product.ui.viewmodel.WaybillProductViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillProductBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillProductViewModel::class)]
    fun bindWaybillListViewModel(waybillProductViewModel: WaybillProductViewModelImpl): ViewModel
}
