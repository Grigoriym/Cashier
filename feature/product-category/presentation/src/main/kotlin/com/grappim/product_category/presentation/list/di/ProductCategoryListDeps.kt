package com.grappim.product_category.presentation.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.FlowRouter
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductCategoryListDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter
}