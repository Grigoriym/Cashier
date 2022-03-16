package com.grappim.scanner.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.scanner.ui.ScannerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ScannerBindsModule {

    @[Binds IntoMap ViewModelKey(ScannerViewModel::class)]
    fun bindScannerViewModel(scannerViewModel: ScannerViewModel): ViewModel
}