package com.grappim.domain.base.flow

fun <T : Any> uninitializedStateFlow(): StateFlowWithoutInitialValue<T> =
    StateFlowWithoutInitialValueImpl()
