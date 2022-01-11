package com.grappim.sign_up.di

import com.grappim.core.utils.ResourceManager
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SignUpRepository

interface SignUpDeps : ComponentDeps {

    fun resourceManager(): ResourceManager
    fun signUpRepository(): SignUpRepository

    fun signUpScreenNavigator(): SignUpScreenNavigator
}