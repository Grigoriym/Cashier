package com.grappim.products.presentation.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.FlowRouter
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductListDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter
}