package com.grappim.bag.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface BagDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
    fun productsRepository():ProductsRepository
    fun basketRepository(): BasketRepository

}