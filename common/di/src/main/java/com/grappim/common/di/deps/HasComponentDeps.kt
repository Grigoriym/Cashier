package com.grappim.common.di.deps

import com.grappim.common.di.ComponentDependenciesProvider

interface HasComponentDeps {
    val deps: ComponentDependenciesProvider
}