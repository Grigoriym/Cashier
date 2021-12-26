package com.grappim.di.deps

import com.grappim.di.ComponentDependenciesProvider

interface HasComponentDeps {
    val deps: ComponentDependenciesProvider
}