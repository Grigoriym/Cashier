package com.grappim.payment_method.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.PaymentRepository
import com.grappim.domain.repository.ProductsRepository
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface PaymentMethodDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
    fun productsRepository(): ProductsRepository
    fun paymentRepository(): PaymentRepository

}