package com.grappim.cashier.feature.productcategory.presentation.list.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import com.grappim.navigation.router.FlowRouter

interface ProductCategoryListDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter
}
