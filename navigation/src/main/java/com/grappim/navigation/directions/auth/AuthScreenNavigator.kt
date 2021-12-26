package com.grappim.navigation.directions.auth

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface AuthScreenNavigator : CommonScreenNavigator {

    fun goToSignUp()
    fun goToSelectStock()

}