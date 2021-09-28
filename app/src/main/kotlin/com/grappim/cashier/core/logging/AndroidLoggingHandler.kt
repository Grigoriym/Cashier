package com.grappim.cashier.core.logging

import android.util.Log
import com.grappim.cashier.BuildConfig
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.LogRecord

class AndroidLoggingHandler : Handler() {

    companion object {
        fun setup() {
            val rootLogger = LogManager.getLogManager().getLogger("")
            rootLogger.handlers.forEach {
                rootLogger.removeHandler(it)
            }
            rootLogger.addHandler(AndroidLoggingHandler())
            rootLogger.level = Level.FINE
        }
    }

    override fun publish(record: LogRecord?) {
        record?.let {
            val tag = it.loggerName
            val level = getAndroidLevel(it.level)
            val message = it.thrown?.let { thrown ->
                "${it.message}: ${Log.getStackTraceString(thrown)}"
            } ?: record.message

            try {
                Log.println(level, tag, message)
            } catch (e: RuntimeException) {
                Log.e(this::class.java.simpleName, "Error logging message", e)
            }
        }
    }

    override fun flush() {
    }

    override fun close() {
    }

    private fun getAndroidLevel(level: Level): Int =
        when (level.intValue()) {
            Level.SEVERE.intValue() -> Log.ERROR
            Level.WARNING.intValue() -> Log.WARN
            Level.INFO.intValue() -> Log.INFO
            Level.FINE.intValue() -> Log.DEBUG
            else -> Log.DEBUG
        }

    override fun isLoggable(record: LogRecord?): Boolean =
        super.isLoggable(record) && BuildConfig.DEBUG
}