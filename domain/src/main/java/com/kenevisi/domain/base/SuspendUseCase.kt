package com.kenevisi.domain.base

import com.kenevisi.core.exceptions.ResourceState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<IN, OUT>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(input: IN): ResourceState<OUT> {
        return withContext(coroutineDispatcher) {
            try {
                val result = execute(input)
                ResourceState.Success(result)
            } catch (e: Exception) {
                ResourceState.Failure(e)
            }
        }
    }

    abstract suspend fun execute(input: IN): OUT
}