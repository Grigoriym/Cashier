package com.grappim.common.di

import com.grappim.common.di.deps.ComponentDeps

typealias ComponentDependenciesProvider = Map<Class<out ComponentDeps>, @JvmSuppressWildcards ComponentDeps>