package ru.mkr.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.usecase.FlowUseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTasksLoad @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<EntityTask>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Resource<List<EntityTask>>> {
        return repositoryTasks.getTasks()
    }
}