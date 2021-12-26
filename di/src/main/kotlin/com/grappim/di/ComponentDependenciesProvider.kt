package com.grappim.di

import com.grappim.di.deps.ComponentDeps

typealias ComponentDependenciesProvider = Map<Class<out ComponentDeps>, @JvmSuppressWildcards ComponentDeps>