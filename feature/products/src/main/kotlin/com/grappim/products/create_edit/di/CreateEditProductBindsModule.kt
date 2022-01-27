package com.grappim.products.create_edit.di

import com.grappim.products.create_edit.ui.viewmodel.CreateEditProductViewModelImpl
import dagger.Module

@Module
interface CreateEditProductBindsModule {

    fun provideCreateProductAssistedViewModelFactory(): CreateEditProductViewModelImpl.Factory

}