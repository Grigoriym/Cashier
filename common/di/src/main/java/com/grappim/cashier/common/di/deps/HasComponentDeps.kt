package com.grappim.cashier.common.di.deps

import com.grappim.cashier.common.di.ComponentDependenciesProvider

interface HasComponentDeps {
    val deps: ComponentDependenciesProvider
}
