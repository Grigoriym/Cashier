package com.grappim.sign_up.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface SignUpScreenNavigator : CommonScreenNavigator {

    fun returnToAuthFromSignUp()

}