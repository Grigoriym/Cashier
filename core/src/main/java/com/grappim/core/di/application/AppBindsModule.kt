package com.grappim.core.di.application

import androidx.lifecycle.ViewModelProvider
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindViewModelFactory(multiViewModelFactory: MultiViewModelFactory): ViewModelProvider.Factory


}