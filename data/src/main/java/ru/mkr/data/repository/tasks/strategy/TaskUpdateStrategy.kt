package ru.mkr.data.repository.tasks.strategy

import kotlinx.coroutines.flow.first
import ru.mkr.data.repository.base.strategy.ActionDataStrategy
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.mapper.MapperTask
import ru.mkr.data.repository.tasks.request.TaskUpdateRequest
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import javax.inject.Inject

class TaskUpdateStrategy @Inject constructor(
    private val localDataSource: LocalDataSourceTasks,
    private val remoteDataSource: RemoteDataSourceTasks,
    private val mapperTask: MapperTask
) : ActionDataStrategy<
        EntityTask,
        Unit,
        TaskUpdateRequest>() {

    override suspend fun fetchFromRemote(request: TaskUpdateRequest): Resource<Unit?> {
        return remoteDataSource.update(request.taskId, request.task)
    }

    override suspend fun handleResult(request: TaskUpdateRequest, data: Unit?): EntityTask? {
        val taskApi = request.task
        taskApi._id = request.taskId
        localDataSource.add(mapperTask.fromApiToDb(taskApi))
        val taskDb = localDataSource.get(request.taskId).first()
        return mapperTask.fromDbToDomain(taskDb)
    }
}