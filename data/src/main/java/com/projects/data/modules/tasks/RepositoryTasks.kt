package com.projects.data.modules.tasks

import kotlinx.coroutines.flow.Flow
import com.projects.data.modules.base.RepositoryBase
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.data.modules.tasks.request.*
import com.projects.data.modules.tasks.strategy.*
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryTasks
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