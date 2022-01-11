package com.grappim.products.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductsRootDeps : ComponentDeps {

    fun productsScreenNavigator(): ProductsScreenNavigator
    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun generalStorage(): GeneralStorage
    fun productCategoryRepository(): ProductCategoryRepository

}