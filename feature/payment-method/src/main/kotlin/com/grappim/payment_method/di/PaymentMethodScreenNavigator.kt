package com.grappim.payment_method.di

import com.grappim.navigation.CommonScreenNavigator

interface PaymentMethodScreenNavigator : CommonScreenNavigator {

    fun fromPaymentMethodToSales()

}