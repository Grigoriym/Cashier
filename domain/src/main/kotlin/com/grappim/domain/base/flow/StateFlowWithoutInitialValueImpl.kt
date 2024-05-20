package com.grappim.domain.base.flow

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class StateFlowWithoutInitialValueImpl<T : Any> : StateFlowWithoutInitialValue<T> {

    private val mutable = MutableStateFlow<T?>(null)

    override suspend fun collect(collector: FlowCollector<T>) = mutable
        .filterNotNull()
        .collect(collector)

    override val value: T?
        get() = mutable.value

    override fun propose(value: T) {
        mutable.value = value
    }
}
