package com.grappim.products.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.date_time.DateTimeIsoInstant
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import java.time.format.DateTimeFormatter

interface ProductsRootDeps : ComponentDeps {

    fun productsScreenNavigator(): ProductsScreenNavigator
    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun generalStorage(): GeneralStorage
    fun productCategoryRepository(): ProductCategoryRepository

    @DateTimeIsoInstant
    fun dtfIso(): DateTimeFormatter

}