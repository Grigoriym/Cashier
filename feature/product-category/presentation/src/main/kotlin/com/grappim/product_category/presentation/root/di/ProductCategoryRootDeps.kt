package com.grappim.product_category.presentation.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductCategoryRootDeps : ComponentDeps {

    fun productCategoryScreenNavigator(): ProductCategoryScreenNavigator
    fun productCategoryRepository(): ProductCategoryRepository
}