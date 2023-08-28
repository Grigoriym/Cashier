package com.grappim.productcategory.presentation.list.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.productcategory.presentation.list.ui.viewmodel.ProductCategoryListViewModel
import com.grappim.productcategory.presentation.list.ui.viewmodel.ProductCategoryListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductCategoryListBindsModule {

    @[Binds IntoMap ViewModelKey(ProductCategoryListViewModel::class)]
    fun bindProductCategoryListViewModel(
        productCategoryListViewModel: ProductCategoryListViewModelImpl
    ): ViewModel
}
