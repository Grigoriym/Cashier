package com.grappim.cashier.common.di

import com.grappim.cashier.common.di.deps.ComponentDeps

typealias ComponentDependenciesProvider =
    Map<Class<out ComponentDeps>, @JvmSuppressWildcards ComponentDeps>
