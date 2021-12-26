package com.grappim.core.di.components_deps.navigation

import com.grappim.di.deps.ComponentDeps
import com.grappim.navigation.directions.sign_up.SignUpScreenNavigator

interface SignUpNavigationDeps : ComponentDeps {

    fun signUpScreenNavigator(): SignUpScreenNavigator

}