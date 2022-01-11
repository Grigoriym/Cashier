package com.grappim.product_category.presentation.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator

interface ProductCategoryListDeps : ComponentDeps {

    fun productCategoryScreenNavigator(): ProductCategoryScreenNavigator

    fun productCategoryRepository(): ProductCategoryRepository
}