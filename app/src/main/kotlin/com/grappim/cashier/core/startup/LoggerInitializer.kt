package com.grappim.cashier.core.startup

import android.content.Context
import androidx.startup.Initializer
import com.grappim.cashier.core.logging.AndroidLoggingHandler

class LoggerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        AndroidLoggingHandler.setup()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        emptyList()
}
