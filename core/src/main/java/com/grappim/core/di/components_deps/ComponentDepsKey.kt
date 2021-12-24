package com.grappim.core.di.components_deps

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDepsKey(
    val value: KClass<out ComponentDeps>
)
