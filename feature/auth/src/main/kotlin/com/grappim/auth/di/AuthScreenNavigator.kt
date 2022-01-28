package com.grappim.auth.di

import com.grappim.navigation.CommonScreenNavigator

interface AuthScreenNavigator : CommonScreenNavigator {

    fun goToSignUp()
    fun goToSelectStock()

}