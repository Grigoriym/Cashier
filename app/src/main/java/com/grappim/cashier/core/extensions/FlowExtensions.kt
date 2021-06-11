package com.grappim.cashier.core.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

fun interval(
    time: Long,
    timeUnit: TimeUnit
): Flow<Long> = flow {

    var counter: Long = 0

    val delayTime = when (timeUnit) {
        TimeUnit.MICROSECONDS -> time / 1000
        TimeUnit.NANOSECONDS -> time / 1_000_000
        TimeUnit.SECONDS -> time * 1000
        TimeUnit.MINUTES -> 60 * time * 1000
        TimeUnit.HOURS -> 60 * 60 * time * 1000
        TimeUnit.DAYS -> 24 * 60 * 60 * time * 1000
        else -> time
    }

    while (true) {
        delay(delayTime)
        emit(counter++)
    }

}