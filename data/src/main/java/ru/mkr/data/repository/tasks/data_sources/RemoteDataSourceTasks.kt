package ru.mkr.data.repository.tasks.data_sources

import ru.mkr.data.network.RemoteDataSourceBase
import ru.mkr.data.network.api.ApiRequests
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import javax.inject.Inject

class RemoteDataSourceTasks @Inject constructor(var retrofitApi: ApiRequests) : RemoteDataSourceBase() {

    public suspend fun getAll() = getResult { retrofitApi.getAll() }

    public suspend fun get(id: String) = getResult { retrofitApi.get(id) }

    public suspend fun add(task: EntityApiTask) = getResult { retrofitApi.add(task) }

    public suspend fun delete(id: String) = getResult { retrofitApi.delete(id) }

    public suspend fun update(id: String, task: EntityApiTask) = getResult { retrofitApi.update(id, task) }
}