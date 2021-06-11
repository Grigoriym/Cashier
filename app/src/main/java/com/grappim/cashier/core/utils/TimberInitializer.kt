package com.grappim.cashier.core.utils

import android.content.Context
import androidx.startup.Initializer
import com.grappim.cashier.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val tree: Timber.Tree = if (BuildConfig.DEBUG) {
            DevelopmentTree()
        } else {
            ProductionTree()
        }
        Timber.plant(tree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    private class DevelopmentTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String {
            return String.format(
                "Cashier-${BuildConfig.BUILD_TYPE}:C:%s:%s",
                super.createStackElementTag(element),
                element.lineNumber
            )
        }
    }

    private class ProductionTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        }
    }
}