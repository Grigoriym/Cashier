package com.grappim.payment_method.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.basket.GetBasketItemsUseCase
import com.grappim.domain.repository.PaymentRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface PaymentMethodDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter

    fun paymentRepository(): PaymentRepository

    fun getBasketItemsUseCase(): GetBasketItemsUseCase

}