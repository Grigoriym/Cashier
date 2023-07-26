package com.grappim.sign_up_presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.password.PasswordManager
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import retrofit2.Retrofit

interface SignUpDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
    fun passwordManager(): PasswordManager

    fun retrofit(): Retrofit
    @QualifierAuthApi
    fun authApi(): AuthApi
}