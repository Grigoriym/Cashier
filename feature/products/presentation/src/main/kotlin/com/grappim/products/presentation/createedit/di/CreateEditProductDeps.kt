package com.grappim.products.presentation.createedit.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.datetime.DateTimeIsoInstant
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.navigation.router.FlowRouter
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
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
}
