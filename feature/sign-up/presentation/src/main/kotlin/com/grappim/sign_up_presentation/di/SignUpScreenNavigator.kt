package com.grappim.sign_up_presentation.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface SignUpScreenNavigator : CommonScreenNavigator {

    fun returnToAuthFromSignUp()

}