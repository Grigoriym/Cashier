package com.grappim.sign_up_presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.core.utils.ResourceManager
import com.grappim.domain.password.PasswordManager
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi

interface SignUpDeps : ComponentDeps {

    fun resourceManager(): ResourceManager

    @QualifierAuthApi
    fun authApi(): AuthApi
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
    fun passwordManager(): PasswordManager
}