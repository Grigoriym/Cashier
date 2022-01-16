package com.grappim.core.di.components_deps

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.logger.logD

inline fun <reified T : ComponentDeps> Fragment.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDeps> AppCompatActivity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

fun Fragment.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDeps?) {
        current = current?.parentFragment
    }
    logD("$this componentDeps current: $current")

    val hasDaggerProviders = current ?: when {
        activity is HasComponentDeps -> activity as HasComponentDeps
        activity?.application is HasComponentDeps -> activity?.application as HasComponentDeps
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    logD("$this componentDeps hasDaggerProviders: $hasDaggerProviders")
    return hasDaggerProviders.deps
}

fun AppCompatActivity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    return (application as HasComponentDeps).deps
}