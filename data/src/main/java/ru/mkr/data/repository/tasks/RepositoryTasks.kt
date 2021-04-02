package ru.mkr.data.repository.tasks

import kotlinx.coroutines.flow.Flow
import ru.mkr.data.repository.base.RepositoryBase
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.data.repository.tasks.request.*
import ru.mkr.data.repository.tasks.strategy.*
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryTasks
import javax.inject.Inject

class RepositoryTasks @Inject constructor(
    private val taskListLoadStrategy: TaskListLoadStrategy,
    private val taskLoadStrategy: TaskLoadStrategy,
    private val taskAddStrategy: TaskAddStrategy,
    private val taskDeleteStrategy: TaskDeleteStrategy,
    private val taskUpdateStrategy: TaskUpdateStrategy
) : RepositoryBase(), IRepositoryTasks {

    override fun getTasks(): Flow<Resource<List<EntityTask>>> = taskListLoadStrategy.execute(TaskListRequest())

    override fun getTask(id: String): Flow<Resource<EntityTask>> = taskLoadStrategy.execute(TaskRequest(id))

    override suspend fun addTask(task: EntityTask): Resource<EntityTask> = taskAddStrategy.execute(TaskAddRequest(EntityApiTask(task.id, task.title, task.dateTime)))

    override suspend fun updateTask(task: EntityTask): Resource<EntityTask> = taskUpdateStrategy.execute(TaskUpdateRequest(task.id!!, EntityApiTask(null, task.title, task.dateTime)))

    override suspend fun deleteTask(taskId: String): Resource<Unit> = taskDeleteStrategy.execute(TaskDeleteRequest(taskId))
}