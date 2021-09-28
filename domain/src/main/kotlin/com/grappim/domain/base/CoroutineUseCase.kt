package com.grappim.domain.base

import com.grappim.logger.logE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class CoroutineUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param params the input parameters to run the use case with
     */
    suspend operator fun invoke(params: P): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(params).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            logE(e)
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
