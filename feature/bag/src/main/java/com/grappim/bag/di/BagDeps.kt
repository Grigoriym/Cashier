package com.grappim.bag.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface BagDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
    fun productsRepository():ProductsRepository
    fun basketRepository(): BasketRepository

}