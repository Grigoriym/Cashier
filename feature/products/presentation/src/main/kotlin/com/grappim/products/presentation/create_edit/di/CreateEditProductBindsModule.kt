package com.grappim.products.presentation.create_edit.di

import com.grappim.products.presentation.create_edit.ui.viewmodel.CreateEditProductViewModelImpl
import dagger.Module

@Module
interface CreateEditProductBindsModule {

    fun provideCreateProductAssistedViewModelFactory(): CreateEditProductViewModelImpl.Factory

}