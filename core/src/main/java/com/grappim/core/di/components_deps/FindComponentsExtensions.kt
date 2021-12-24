package com.grappim.core.di.components_deps

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

inline fun <reified T : ComponentDeps> AppCompatActivity.findComponentDependencies(): T =
    findComponentDepsProvider()[T::class.java] as T

inline fun <reified T : ComponentDeps> Fragment.findComponentDependencies(): T{
    val provider = findComponentDepsProvider()[T::class.java] as T
    return provider
}

fun AppCompatActivity.findComponentDepsProvider(): ComponentDependenciesProvider {
    return (application as HasComponentDeps).deps
}

fun Fragment.findComponentDepsProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDeps?) {
        current = current?.parentFragment
    }

    val hasDaggerProviders = current ?: when {
        activity is HasComponentDeps -> activity as HasComponentDeps
        activity?.application is HasComponentDeps -> activity?.application as HasComponentDeps
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.deps
}