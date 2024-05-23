package com.projects.data.modules.tasks.strategy

import kotlinx.coroutines.flow.first
import com.projects.data.modules.base.strategy.ActionDataStrategy
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.mapper.MapperTask
import com.projects.data.modules.tasks.request.TaskAddRequest
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import javax.inject.Inject

class TaskAddStrategy @Inject constructor(
    private val localDataSource: LocalDataSourceTasks,
    private val remoteDataSource: RemoteDataSourceTasks,
    private val mapper: MapperTask
) : ActionDataStrategy<
        EntityTask,
        EntityApiTask,
        TaskAddRequest>() {

    override suspend fun fetchFromRemote(request: TaskAddRequest): Resource<EntityApiTask?> {
        return remoteDataSource.add(request.task)
    }

    override suspend fun handleResult(request: TaskAddRequest, data: EntityApiTask?): EntityTask? {
        if(data != null) {
            localDataSource.add(mapper.fromApiToDb(data))
            val dbData = localDataSource.observeById(data._id!!).first()
            return mapper.fromDbToDomain(dbData)
        }
        return null
    }
}