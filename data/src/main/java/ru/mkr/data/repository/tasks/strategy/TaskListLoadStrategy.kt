package ru.mkr.data.repository.tasks.strategy

import com.google.android.gms.common.util.CollectionUtils
import kotlinx.coroutines.flow.Flow
import ru.mkr.data.repository.tasks.database.EntityDbTask
import ru.mkr.data.repository.tasks.mapper.MapperTasks
import ru.mkr.domain.entity.EntityTask
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.data.repository.base.cache.CachePeriodShort
import ru.mkr.data.repository.base.cache.SimpleCacheStrategy
import ru.mkr.data.repository.base.strategy.LoadDataStrategy
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.request.TaskListRequest
import ru.mkr.domain.entity.Resource
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
        return localDataSource.getAll()
    }

    override suspend fun mapDbData(
        request: TaskListRequest,
        data: List<EntityDbTask>?
    ): List<EntityTask>? {
        return if(!CollectionUtils.isEmpty(data)) mapper.fromDbToDomain(data!!)
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