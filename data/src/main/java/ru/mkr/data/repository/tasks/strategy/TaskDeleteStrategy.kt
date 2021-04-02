package ru.mkr.data.repository.tasks.strategy

import ru.mkr.data.repository.base.strategy.ActionDataStrategy
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.request.TaskDeleteRequest
import ru.mkr.domain.entity.Resource
import javax.inject.Inject

class TaskDeleteStrategy @Inject constructor(
    private val localDataSource: LocalDataSourceTasks,
    private val remoteDataSource: RemoteDataSourceTasks
) : ActionDataStrategy<
        Unit,
        Unit,
        TaskDeleteRequest>() {

    override suspend fun fetchFromRemote(request: TaskDeleteRequest): Resource<Unit?> {
        return remoteDataSource.delete(request.taskId)
    }

    override suspend fun handleResult(request: TaskDeleteRequest, data: Unit?): Unit? {
        localDataSource.delete(request.taskId)
        return null
    }
}