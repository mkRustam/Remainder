package com.projects.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryTasks
import com.projects.domain.usecase.FlowUseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTasksLoad @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<EntityTask>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Resource<List<EntityTask>>> {
        return repositoryTasks.getTasks()
    }
}