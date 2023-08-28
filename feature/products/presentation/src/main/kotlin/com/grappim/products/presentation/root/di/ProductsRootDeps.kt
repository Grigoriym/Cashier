package com.grappim.products.presentation.root.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.datetime.DateTimeIsoInstant
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
import java.time.format.DateTimeFormatter

interface ProductsRootDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun generalRepository(): GeneralRepository
    fun generalStorage(): GeneralStorage
    fun productCategoryRepository(): ProductCategoryRepository

    @DateTimeIsoInstant
    fun dtfIso(): DateTimeFormatter
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter

}
