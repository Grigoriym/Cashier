package com.grappim.domain.base.flow

import kotlinx.coroutines.flow.Flow

interface StateFlowWithoutInitialValue<T : Any> : Flow<T> {

    val value: T?

    fun propose(value: T)
}