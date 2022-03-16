package com.grappim.sign_up_presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.core.utils.ResourceManager
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi

interface SignUpDeps : ComponentDeps {

    fun resourceManager(): ResourceManager

    fun signUpScreenNavigator(): SignUpScreenNavigator

    @QualifierAuthApi
    fun authApi(): AuthApi
    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
}