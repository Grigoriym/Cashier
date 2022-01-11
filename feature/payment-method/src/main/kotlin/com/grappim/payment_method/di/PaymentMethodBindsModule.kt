package com.grappim.payment_method.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.payment_method.ui.PaymentMethodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PaymentMethodBindsModule {

    @[Binds IntoMap ViewModelKey(PaymentMethodViewModel::class)]
    fun provideAuthViewModel(paymentMethodViewModel: PaymentMethodViewModel): ViewModel

}