package com.grappim.sign_up.di

import com.grappim.core.utils.ResourceManager
import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.SignUpRepository
import com.grappim.navigation.directions.sign_up.SignUpScreenNavigator

interface SignUpDeps : ComponentDeps {

    fun resourceManager(): ResourceManager
    fun signUpRepository(): SignUpRepository

    fun signUpScreenNavigator(): SignUpScreenNavigator
}