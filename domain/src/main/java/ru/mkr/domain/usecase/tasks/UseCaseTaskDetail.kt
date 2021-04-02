package ru.mkr.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.usecase.FlowUseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTaskDetail  @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<String, EntityTask>(dispatcher) {

    override fun execute(parameters: String): Flow<Resource<EntityTask>> {
        return repositoryTasks.getTask(parameters)
    }
}