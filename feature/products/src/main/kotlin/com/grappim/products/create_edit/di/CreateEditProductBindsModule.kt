package com.grappim.products.create_edit.di

import com.grappim.products.create_edit.ui.CreateEditProductViewModel
import dagger.Module

@Module
interface CreateEditProductBindsModule {

    fun provideCreateProductAssitedViewModelFactory(): CreateEditProductViewModel.Factory

}