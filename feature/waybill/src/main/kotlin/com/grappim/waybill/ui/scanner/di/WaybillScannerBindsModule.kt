package com.grappim.waybill.ui.scanner.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.scanner.ui.WaybillScannerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WaybillScannerBindsModule {

    @[Binds IntoMap ViewModelKey(WaybillScannerViewModel::class)]
    fun bindWaybillScannerViewModel(
        waybillScannerViewModel: WaybillScannerViewModel
    ):ViewModel

}