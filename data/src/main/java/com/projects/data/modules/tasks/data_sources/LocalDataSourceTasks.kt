package com.projects.data.modules.tasks.data_sources

import kotlinx.coroutines.flow.distinctUntilChanged
import com.projects.data.database.LocalDataSourceBase
import com.projects.data.modules.tasks.database.DaoTasks
import com.projects.data.modules.tasks.database.EntityDbTask
import javax.inject.Inject

class LocalDataSourceTasks @Inject constructor(var daoTasks: DaoTasks) : LocalDataSourceBase() {

    suspend fun getAll() = daoTasks.getAll()

    fun get(id: String) = daoTasks.get(id).distinctUntilChanged()

    suspend fun add(task: EntityDbTask) {
        makeExpire(task)
        daoTasks.insertOrUpdate(task)
    }

    suspend fun addAll(tasks: List<EntityDbTask>) {
        makeExpireList(tasks)
        daoTasks.refresh(tasks)
    }

    suspend fun delete(id: String) = daoTasks.delete(id)

    suspend fun deleteAll() = daoTasks.deleteAll()
}