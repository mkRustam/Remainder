package com.projects.data.modules.tasks.data_sources

import com.projects.data.network.RemoteDataSourceBase
import com.projects.data.network.api.ApiRequests
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import javax.inject.Inject

class RemoteDataSourceTasks @Inject constructor(private var retrofitApi: ApiRequests) : RemoteDataSourceBase() {

    suspend fun getAll() = getResult { retrofitApi.getAll() }

    suspend fun get(id: String) = getResult { retrofitApi.get(id) }

    suspend fun add(task: EntityApiTask) = getResult { retrofitApi.add(task) }

    suspend fun delete(id: String) = getResult { retrofitApi.delete(id) }

    suspend fun update(id: String, task: EntityApiTask) = getResult { retrofitApi.update(id, task) }
}