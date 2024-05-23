package com.projects.data.modules.tasks.strategy

import kotlinx.coroutines.flow.Flow
import com.projects.data.modules.tasks.database.EntityDbTask
import com.projects.data.modules.tasks.mapper.MapperTasks
import com.projects.domain.entity.EntityTask
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.data.modules.base.cache.CachePeriodShort
import com.projects.data.modules.base.cache.SimpleCacheStrategy
import com.projects.data.modules.base.strategy.LoadDataStrategy
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.request.TaskListRequest
import com.projects.domain.entity.Resource
import javax.inject.Inject

class TaskListLoadStrategy @Inject constructor(
    private val localDataSource: LocalDataSourceTasks,
    private val remoteDataSource: RemoteDataSourceTasks,
    private val mapper: MapperTasks,
    @CachePeriodShort private val cacheController: SimpleCacheStrategy
) : LoadDataStrategy<
        List<EntityDbTask>,
        List<EntityTask>,
        List<EntityApiTask>,
        TaskListRequest>() {

    override suspend fun fetchFromLocal(request: TaskListRequest): Flow<List<EntityDbTask>?> {
        return localDataSource.observeAll()
    }

    override suspend fun mapDbData(
        request: TaskListRequest,
        data: List<EntityDbTask>?
    ): List<EntityTask>? {
        return if(!data.isNullOrEmpty()) mapper.fromDbToDomain(data)
        else null
    }

    override suspend fun fetchFromRemote(request: TaskListRequest): Resource<List<EntityApiTask>?> {
        return remoteDataSource.getAll()
    }

    override suspend fun mapApiData(
        request: TaskListRequest,
        data: List<EntityApiTask>?
    ): List<EntityDbTask>? {
        return if(data != null) mapper.fromApiToDb(data)
        else null
    }

    override suspend fun saveData(request: TaskListRequest, data: List<EntityDbTask>?) {
        localDataSource.deleteAll()
        if(data != null) localDataSource.addAll(data)
    }

    override suspend fun isActualData(data: List<EntityDbTask>): Boolean {
        return cacheController.isRelevant(data)
    }
}