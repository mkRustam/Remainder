package com.projects.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import com.projects.domain.entity.Resource

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<Resource<R>> = execute(parameters)
        .catch { e -> emit(Resource.error(e.message ?: e.toString(), null)) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<Resource<R>>
}