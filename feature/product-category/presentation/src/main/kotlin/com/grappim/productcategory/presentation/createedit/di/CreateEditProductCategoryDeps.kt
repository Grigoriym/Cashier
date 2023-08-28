package com.grappim.productcategory.presentation.createedit.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.router.FlowRouter
import com.grappim.productcategory.domain.repository.ProductCategoryRepository

interface CreateEditProductCategoryDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter
}
