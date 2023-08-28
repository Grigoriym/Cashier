package com.grappim.paymentmethod.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.paymentmethod.domain.repository.PaymentRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface PaymentMethodDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter

    fun paymentRepository(): PaymentRepository
    fun basketRepository(): BagRepository

}
