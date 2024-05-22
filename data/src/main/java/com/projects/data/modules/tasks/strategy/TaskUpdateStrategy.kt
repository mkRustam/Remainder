package com.projects.data.modules.tasks.strategy

import kotlinx.coroutines.flow.first
import com.projects.data.modules.base.strategy.ActionDataStrategy
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.mapper.MapperTask
import com.projects.data.modules.tasks.request.TaskUpdateRequest
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
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