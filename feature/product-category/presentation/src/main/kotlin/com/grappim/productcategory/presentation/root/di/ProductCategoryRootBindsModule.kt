package com.grappim.productcategory.presentation.root.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.productcategory.presentation.root.ui.ProductCategoryRootViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductCategoryRootBindsModule {

    @[Binds IntoMap ViewModelKey(ProductCategoryRootViewModel::class)]
    fun bindProductCategoryRootViewModel(
        productCategoryRootViewModel: ProductCategoryRootViewModel
    ): ViewModel
}
