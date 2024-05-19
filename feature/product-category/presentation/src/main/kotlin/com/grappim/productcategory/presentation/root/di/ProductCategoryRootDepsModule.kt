package com.grappim.productcategory.presentation.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.productcategory.presentation.createedit.di.CreateEditProductCategoryDeps
import com.grappim.productcategory.presentation.list.di.ProductCategoryListDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductCategoryRootDepsModule {

    @[Binds IntoMap ComponentDepsKey(ProductCategoryListDeps::class)]
    fun bindProductCategoryListDeps(
        productCategoryRootComponent: ProductCategoryRootComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(CreateEditProductCategoryDeps::class)]
    fun bindCreateEditProductCategoryDeps(
        productCategoryRootComponent: ProductCategoryRootComponent
    ): ComponentDeps
}
