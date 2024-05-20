package com.grappim.products.presentation.root.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.products.presentation.root.ui.ProductsRootViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductsRootBindsModule {

    @[Binds IntoMap ViewModelKey(ProductsRootViewModel::class)]
    fun bindAuthViewModel(productsRootViewModel: ProductsRootViewModel): ViewModel
}
