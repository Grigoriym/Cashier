package com.grappim.products.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.products.create_edit.di.CreateEditProductDeps
import com.grappim.products.list.di.ProductListDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductsRootDepsModule {

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(ProductListDeps::class)]
    fun bindProductListDeps(
        productsRootComponent: ProductsRootComponent
    ): com.grappim.common.di.deps.ComponentDeps

    @[Binds IntoMap com.grappim.common.di.deps.ComponentDepsKey(CreateEditProductDeps::class)]
    fun bindCreateProductDeps(
        productsRootComponent: ProductsRootComponent
    ): com.grappim.common.di.deps.ComponentDeps

}