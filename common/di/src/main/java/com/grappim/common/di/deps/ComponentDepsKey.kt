package com.grappim.common.di.deps

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDepsKey(
    val value: KClass<out ComponentDeps>
)
