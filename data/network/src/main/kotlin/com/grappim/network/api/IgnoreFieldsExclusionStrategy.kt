package com.grappim.network.api

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.grappim.common.di.GsonScope
import javax.inject.Inject

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GsonIgnore

@com.grappim.common.di.GsonScope
class IgnoreFieldsExclusionStrategy @Inject constructor() : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes?): Boolean =
        f?.getAnnotation(GsonIgnore::class.java) != null

    override fun shouldSkipClass(clazz: Class<*>?): Boolean = false

}