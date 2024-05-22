package com.projects.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryTasks
import com.projects.domain.usecase.UseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTaskDelete @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<String, Unit>(dispatcher) {

    override suspend fun execute(parameters: String): Resource<Unit> {
        return repositoryTasks.deleteTask(parameters)
    }
}