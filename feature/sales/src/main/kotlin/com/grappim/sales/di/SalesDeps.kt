package com.grappim.sales.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface SalesDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun cashierScreens(): CashierScreens
    fun activityRouter(): ActivityRouter
    fun basketRepository(): BagRepository
}