package com.grappim.core.di.components_deps.navigation

import com.grappim.di.deps.ComponentDeps
import com.grappim.navigation.Navigator

interface NavigationDeps : ComponentDeps {
    fun navigator(): Navigator
}