package com.grappim.core.di.components_deps.navigation

import com.grappim.di.deps.ComponentDeps
import com.grappim.navigation.directions.auth.AuthScreenNavigator

interface AuthNavigationDeps : ComponentDeps {

    fun authScreenNavigator(): AuthScreenNavigator

}