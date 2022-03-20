package com.grappim.sales.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface SalesDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
    fun basketRepository(): BasketRepository
}