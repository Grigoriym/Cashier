package com.grappim.products.list.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.products.list.ui.viewmodel.ProductListViewModel
import com.grappim.products.list.ui.viewmodel.ProductListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductListBindsModule {

    @[Binds IntoMap ViewModelKey(ProductListViewModel::class)]
    fun bindProductListViewModel(productListViewModel: ProductListViewModelImpl): ViewModel

}