package com.grappim.sales.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.basket.GetBasketItemsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.sales.SubtractProductFromBasketUseCase
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface SalesDeps : ComponentDeps {

    fun productsRepository(): ProductsRepository
    fun cashierScreens(): CashierScreens
    fun activityRouter(): ActivityRouter
    fun basketRepository(): BasketRepository

    fun getBasketItemsUseCase(): GetBasketItemsUseCase
    fun searchProductsUseCase(): SearchProductsUseCase
    fun addProductToBasketUseCase(): AddProductToBasketUseCase
    fun subtractProductFromBasketUseCase(): SubtractProductFromBasketUseCase
}