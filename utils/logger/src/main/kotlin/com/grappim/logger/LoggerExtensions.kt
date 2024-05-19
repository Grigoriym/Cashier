package com.grappim.logger

import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

private val Any.logger: Logger
    get() = Logger.getLogger(this::class.java.simpleName)

private const val TAG = "Cashier-app"

private fun defaultLogRecord(msg: String) = LogRecord(Level.FINE, msg).apply {
    loggerName = TAG
}

fun Any.logD(message: String) {
    logger.log(defaultLogRecord(message))
}

fun Any.logD(tag: String, message: String) {
    val logRecord = LogRecord(Level.FINE, message).apply {
        loggerName = "$TAG $tag"
    }
    logger.log(logRecord)
}

fun Any.logE(throwable: Throwable) {
    logger.log(Level.SEVERE, TAG, throwable)
}
