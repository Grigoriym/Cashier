package com.grappim.payment_method.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.payment_method.domain.repository.PaymentRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface PaymentMethodDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter

    fun paymentRepository(): PaymentRepository
    fun basketRepository(): BagRepository

}