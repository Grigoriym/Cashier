package com.grappim.products.presentation.root.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.datetime.DateTimeIsoInstant
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
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
