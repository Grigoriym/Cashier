package com.grappim.payment_method.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.payment_method.ui.PaymentMethodFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        PaymentMethodBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        PaymentMethodDeps::class
    ]
)]
interface PaymentMethodComponent {

    fun inject(paymentMethodFragment: PaymentMethodFragment)

}