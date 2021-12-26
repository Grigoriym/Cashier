package com.grappim.core.di.components_deps

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.grappim.di.ComponentDependenciesProvider
import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.HasComponentDeps
import com.grappim.logger.logD

inline fun <reified T : ComponentDeps> AppCompatActivity.findAppComponentDependencies(): T =
    findAppComponentDepsProvider()[T::class.java] as T

fun AppCompatActivity.findAppComponentDepsProvider(): ComponentDependenciesProvider {
    return (application as HasComponentDeps).deps
}

inline fun <reified T : ComponentDeps> Fragment.findComponentDependencies(): T {
    logD("$this DI T: ${T::class}")
    val provider = findComponentDepsProvider()[T::class.java] as T
    return provider
}

inline fun <reified T : ComponentDeps> Fragment.findActivityComponentDependencies(): T {
    logD("$this DI T: ${T::class}")
    val provider = findActivityComponentDepsProvider()[T::class.java] as T
    return provider
}

fun Fragment.findActivityComponentDepsProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDeps?) {
        current = current?.parentFragment
    }
    logD("$this DI current: $current")

    val hasDaggerProviders = current ?: when {
        activity is HasComponentDeps -> activity as HasComponentDeps
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    logD("$this DI hasDaggerProviders: $hasDaggerProviders | ${hasDaggerProviders.deps}")
    return hasDaggerProviders.deps
}

fun Fragment.findComponentDepsProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDeps?) {
        current = current?.parentFragment
    }
    logD("$this DI current: $current")

    val hasDaggerProviders = current ?: when {
        activity?.application is HasComponentDeps -> activity?.application as HasComponentDeps
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    logD("$this DI hasDaggerProviders: $hasDaggerProviders | ${hasDaggerProviders.deps}")
    return hasDaggerProviders.deps
}