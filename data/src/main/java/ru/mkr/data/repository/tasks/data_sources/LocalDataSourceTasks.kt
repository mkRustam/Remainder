package ru.mkr.data.repository.tasks.data_sources

import kotlinx.coroutines.flow.distinctUntilChanged
import ru.mkr.data.database.LocalDataSourceBase
import ru.mkr.data.repository.tasks.database.DaoTasks
import ru.mkr.data.repository.tasks.database.EntityDbTask
import javax.inject.Inject

class LocalDataSourceTasks @Inject constructor(var daoTasks: DaoTasks) : LocalDataSourceBase() {

    suspend fun getAll() = daoTasks.getAll()

    fun get(id: String) = daoTasks.get(id).distinctUntilChanged()

    suspend fun add(task: EntityDbTask) {
        makeExpire(task)
        daoTasks.insert(task)
    }

    suspend fun addAll(tasks: List<EntityDbTask>) {
        makeExpireList(tasks)
        daoTasks.refresh(tasks)
    }

    suspend fun delete(id: String) = daoTasks.delete(id)

    suspend fun deleteAll() = daoTasks.deleteAll()
}