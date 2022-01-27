package com.grappim.products.root.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.products.ProductsScreenNavigatorImpl
import com.grappim.products.root.ui.ProductsRootViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductsRootBindsModule {

    @[Binds IntoMap ViewModelKey(ProductsRootViewModel::class)]
    fun bindAuthViewModel(productsRootViewModel: ProductsRootViewModel): ViewModel

    @Binds
    fun bindProductsScreenNavigator(
        productsScreenNavigatorImpl: ProductsScreenNavigatorImpl
    ): ProductsScreenNavigator
}