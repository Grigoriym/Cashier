package com.grappim.cashier.feature.productcategory.presentation.createedit.di

import com.grappim.cashier.feature.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import dagger.Module

@Module
interface CreateEditProductCategoryBindsModule {

    fun createEditViewModelAssistedFactory(): CreateEditProductCategoryViewModelImpl.Factory
}
