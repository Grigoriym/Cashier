package com.grappim.product_category.presentation.create_edit.di

import com.grappim.product_category.presentation.create_edit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import dagger.Module

@Module
interface CreateEditProductCategoryBindsModule {

    fun createEditViewModelAssistedFactory(): CreateEditProductCategoryViewModelImpl.Factory
}