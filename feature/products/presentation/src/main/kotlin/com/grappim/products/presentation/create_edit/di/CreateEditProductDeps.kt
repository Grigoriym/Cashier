package com.grappim.products.presentation.create_edit.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.date_time.DateTimeIsoInstant
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.router.FlowRouter
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import java.time.format.DateTimeFormatter

interface CreateEditProductDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun generalStorage(): GeneralStorage
    fun productCategoryRepository(): ProductCategoryRepository
    fun flowRouter(): FlowRouter

    @DateTimeIsoInstant
    fun dtfIso(): DateTimeFormatter
    fun multiViewModelFactory(): MultiViewModelFactory

    fun createProductUseCase(): CreateProductUseCase
    fun editProductUseCase(): EditProductUseCase
    fun getCategoryListInteractor(): GetCategoryListUseCase
}