package com.grappim.product_category.presentation.create_edit.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator

interface CreateEditProductCategoryDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun productCategoryScreenNavigator(): ProductCategoryScreenNavigator
}