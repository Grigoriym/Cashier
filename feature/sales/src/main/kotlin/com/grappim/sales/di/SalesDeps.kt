package com.grappim.sales.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.ProductsRepository

interface SalesDeps : ComponentDeps {

    fun salesScreenNavigator(): SalesScreenNavigator

    fun productsRepository(): ProductsRepository
}