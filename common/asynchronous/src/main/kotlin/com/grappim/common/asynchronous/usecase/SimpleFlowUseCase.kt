package com.grappim.common.asynchronous.usecase

import com.grappim.logger.logE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class SimpleFlowUseCase<in Params, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    operator fun invoke(parameters: Params): Flow<R> = execute(parameters)
        .catch { e ->
            logE(e)
            throw e
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(params: Params): Flow<R>
}
