package ru.mkr.domain.usecase.tasks

import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.usecase.UseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseTaskDelete @Inject constructor(
    private val repositoryTasks: IRepositoryTasks,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<String, Unit>(dispatcher) {

    override suspend fun execute(parameters: String): Resource<Unit> {
        return repositoryTasks.deleteTask(parameters)
    }
}