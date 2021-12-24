package com.grappim.network.api

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.grappim.di.AppScope
import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GsonIgnore

@AppScope
class IgnoreFieldsExclusionStrategy @Inject constructor() : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes?): Boolean =
        f?.getAnnotation(GsonIgnore::class.java) != null

    override fun shouldSkipClass(clazz: Class<*>?): Boolean = false

}

@Module
interface GsonBindsModule {
    @Binds
    fun bindExclusionStrategy(ignoreFieldsExclusionStrategy: IgnoreFieldsExclusionStrategy): ExclusionStrategy
}