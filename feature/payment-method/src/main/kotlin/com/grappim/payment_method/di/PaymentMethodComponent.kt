package com.grappim.payment_method.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
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