package com.grappim.auth.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface AuthScreenNavigator : CommonScreenNavigator {

    fun goToSignUp()
    fun goToSelectStock()

}