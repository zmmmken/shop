package com.kenevisi.domain.base

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class PagingDataUseCase<IN, OUT : Any>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(input: IN) = execute(input).flowOn(coroutineDispatcher)

    abstract fun execute(input: IN): Flow<PagingData<OUT>>
}