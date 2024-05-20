package com.grappim.cashier.feature.productcategory.presentation.root.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.cashier.feature.productcategory.presentation.root.ui.ProductCategoryRootViewModel
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
