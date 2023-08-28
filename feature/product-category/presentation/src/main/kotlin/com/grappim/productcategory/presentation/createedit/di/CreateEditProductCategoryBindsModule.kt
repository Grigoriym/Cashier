package com.grappim.productcategory.presentation.createedit.di

import com.grappim.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import dagger.Module

@Module
interface CreateEditProductCategoryBindsModule {

    fun createEditViewModelAssistedFactory(): CreateEditProductCategoryViewModelImpl.Factory
}
