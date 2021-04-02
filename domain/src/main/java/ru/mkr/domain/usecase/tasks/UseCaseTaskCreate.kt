package ru.mkr.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.usecase.UseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTaskCreate @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<EntityTask, EntityTask>(dispatcher) {

    override suspend fun execute(parameters: EntityTask): Resource<EntityTask> {
        return repositoryTasks.addTask(parameters)
    }
}