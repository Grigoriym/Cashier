package com.grappim.product_category.presentation.create_edit.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.router.FlowRouter
import com.grappim.product_category.domain.interactor.CreateProductCategoryUseCase
import com.grappim.product_category.domain.interactor.EditProductCategoryUseCase
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface CreateEditProductCategoryDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter

    fun createProductCategoryUseCase(): CreateProductCategoryUseCase
    fun editProductCategoryUseCase(): EditProductCategoryUseCase
}