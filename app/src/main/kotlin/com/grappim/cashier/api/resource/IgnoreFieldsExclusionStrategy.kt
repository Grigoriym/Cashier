package com.grappim.cashier.api.resource

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GsonIgnore

class IgnoreFieldsExclusionStrategy : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes?): Boolean =
        f?.getAnnotation(GsonIgnore::class.java) != null

    override fun shouldSkipClass(clazz: Class<*>?): Boolean = false

}