package com.grappim.products.presentation.createedit.di

import com.grappim.products.presentation.createedit.ui.viewmodel.CreateEditProductViewModelImpl
import dagger.Module

@Module
interface CreateEditProductBindsModule {

    fun provideCreateProductAssistedViewModelFactory(): CreateEditProductViewModelImpl.Factory
}
