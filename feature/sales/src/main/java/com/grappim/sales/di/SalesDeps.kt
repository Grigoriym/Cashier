package com.grappim.sales.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.ProductsRepository

interface SalesDeps : ComponentDeps {

    fun salesScreenNavigator(): SalesScreenNavigator

    fun productsRepository(): ProductsRepository
}