package com.grappim.product_category.presentation.create_edit.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.product_category.presentation.create_edit.ui.CreateEditProductCategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateEditProductCategoryBindsModule {

    @[Binds IntoMap ViewModelKey(CreateEditProductCategoryViewModel::class)]
    fun bindCreateEditProductCategoryViewModel(
        createEditProductCategoryViewModel: CreateEditProductCategoryViewModel
    ): ViewModel
}