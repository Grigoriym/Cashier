package com.grappim.cashier.feature.productcategory.presentation.root.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.common.di.deps.ComponentDepsKey
import com.grappim.cashier.feature.productcategory.presentation.createedit.di.CreateEditProductCategoryDeps
import com.grappim.cashier.feature.productcategory.presentation.list.di.ProductCategoryListDeps
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
