package ru.mkr.data.repository.tasks.strategy

import kotlinx.coroutines.flow.Flow
import ru.mkr.data.repository.base.cache.CachePeriodShort
import ru.mkr.data.repository.base.cache.SimpleCacheStrategy
import ru.mkr.data.repository.base.strategy.LoadDataStrategy
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.database.EntityDbTask
import ru.mkr.data.repository.tasks.mapper.MapperTask
import ru.mkr.data.repository.tasks.request.TaskRequest
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
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