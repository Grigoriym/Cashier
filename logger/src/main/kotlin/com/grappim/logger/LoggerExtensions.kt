package com.grappim.logger

import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

private val Any.logger: Logger
    get() = Logger.getLogger(this::class.java.simpleName)

fun Any.logD(message: String) {
    logger.log(Level.FINE, message)
}

fun Any.logD(tag: String, message: String) {
    val logRecord = LogRecord(Level.FINE, message).apply {
        loggerName = tag
    }
    logger.log(logRecord)
}

fun Any.logE(throwable: Throwable) {
    logger.log(Level.SEVERE, "Error", throwable)
}