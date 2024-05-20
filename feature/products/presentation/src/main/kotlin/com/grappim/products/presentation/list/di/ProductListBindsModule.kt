package com.grappim.products.presentation.list.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.products.presentation.list.ui.viewmodel.ProductListViewModel
import com.grappim.products.presentation.list.ui.viewmodel.ProductListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductListBindsModule {

    @[Binds IntoMap ViewModelKey(ProductListViewModel::class)]
    fun bindProductListViewModel(productListViewModel: ProductListViewModelImpl): ViewModel
}
