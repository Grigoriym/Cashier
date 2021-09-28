package com.grappim.domain.base

import com.grappim.logger.logE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in Params, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    operator fun invoke(params: Params): Flow<Result<R>> = execute(params)
        .catch { e ->
            logE(e)
            emit(Result.Error(e))
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(params: Params): Flow<Result<R>>
}
