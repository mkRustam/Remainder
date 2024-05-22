package com.projects.data.modules.tasks.strategy

import com.projects.data.modules.base.strategy.ActionDataStrategy
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.request.TaskDeleteRequest
import com.projects.domain.entity.Resource
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