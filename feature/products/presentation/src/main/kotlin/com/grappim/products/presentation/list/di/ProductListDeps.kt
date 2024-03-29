package com.grappim.products.presentation.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.GeneralRepository
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.navigation.router.FlowRouter
import com.grappim.productcategory.domain.repository.ProductCategoryRepository

interface ProductListDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter
}
