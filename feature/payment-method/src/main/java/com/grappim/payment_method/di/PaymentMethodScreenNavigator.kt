package com.grappim.payment_method.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface PaymentMethodScreenNavigator : CommonScreenNavigator {

    fun fromPaymentMethodToSales()

}