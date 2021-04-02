package ru.mkr.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource

interface IRepositoryTasks {
    fun getTasks(): Flow<Resource<List<EntityTask>>>
    fun getTask(id: String): Flow<Resource<EntityTask>>
    suspend fun addTask(task: EntityTask): Resource<EntityTask>
    suspend fun updateTask(task: EntityTask): Resource<EntityTask>
    suspend fun deleteTask(taskId: String): Resource<Unit>
}