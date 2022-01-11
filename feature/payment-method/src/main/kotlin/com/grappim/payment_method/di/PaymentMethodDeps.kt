package com.grappim.payment_method.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.PaymentRepository
import com.grappim.domain.repository.ProductsRepository

interface PaymentMethodDeps : ComponentDeps {

    fun paymentMethodScreenNavigator(): PaymentMethodScreenNavigator
    fun productsRepository(): ProductsRepository
    fun paymentRepository(): PaymentRepository

}