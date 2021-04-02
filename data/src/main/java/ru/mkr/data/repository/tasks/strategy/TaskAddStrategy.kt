package ru.mkr.data.repository.tasks.strategy

import kotlinx.coroutines.flow.first
import ru.mkr.data.repository.base.strategy.ActionDataStrategy
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.mapper.MapperTask
import ru.mkr.data.repository.tasks.request.TaskAddRequest
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
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
            val dbData = localDataSource.get(data._id!!).first()
            return mapper.fromDbToDomain(dbData)
        }
        return null
    }
}