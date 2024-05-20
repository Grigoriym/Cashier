package com.grappim.products.presentation.root.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.common.di.deps.ComponentDepsKey
import com.grappim.products.presentation.createedit.di.CreateEditProductDeps
import com.grappim.products.presentation.list.di.ProductListDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductsRootDepsModule {

    @[Binds IntoMap ComponentDepsKey(ProductListDeps::class)]
    fun bindProductListDeps(productsRootComponent: ProductsRootComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(CreateEditProductDeps::class)]
    fun bindCreateProductDeps(productsRootComponent: ProductsRootComponent): ComponentDeps
}
