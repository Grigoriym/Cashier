package com.grappim.payment_method.di

import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.domain.interactor.payment.MakePaymentUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PaymentMethodInteractorModule {
    @Binds
    fun bindMakePaymentUseCase(
        makePaymentUseCaseImpl: MakePaymentUseCaseImpl
    ): MakePaymentUseCase
}