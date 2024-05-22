package com.projects.data.modules.tasks.strategy

import kotlinx.coroutines.flow.Flow
import com.projects.data.modules.base.cache.CachePeriodShort
import com.projects.data.modules.base.cache.SimpleCacheStrategy
import com.projects.data.modules.base.strategy.LoadDataStrategy
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.database.EntityDbTask
import com.projects.data.modules.tasks.mapper.MapperTask
import com.projects.data.modules.tasks.request.TaskRequest
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import javax.inject.Inject

class TaskLoadStrategy @Inject constructor(
    private val localDataSource: LocalDataSourceTasks,
    private val remoteDataSource: RemoteDataSourceTasks,
    private val mapper: MapperTask,
    @CachePeriodShort private val cacheController: SimpleCacheStrategy
) : LoadDataStrategy<
        EntityDbTask,
        EntityTask,
        EntityApiTask,
        TaskRequest>() {

    override suspend fun fetchFromLocal(request: TaskRequest): Flow<EntityDbTask?> {
        return localDataSource.get(request.taskId)
    }

    override suspend fun mapDbData(request: TaskRequest, data: EntityDbTask?): EntityTask? {
        return if(data != null) mapper.fromDbToDomain(data)
        else null
    }

    override suspend fun fetchFromRemote(request: TaskRequest): Resource<EntityApiTask?> {
        return remoteDataSource.get(request.taskId)
    }

    override suspend fun mapApiData(request: TaskRequest, data: EntityApiTask?): EntityDbTask? {
        return if(data != null) mapper.fromApiToDb(data)
        else null
    }

    override suspend fun saveData(request: TaskRequest, data: EntityDbTask?) {
        if(data != null) localDataSource.add(data)
        else localDataSource.delete(request.taskId)
    }

    override suspend fun isActualData(data: EntityDbTask): Boolean {
        return cacheController.isRelevant(data)
    }
}