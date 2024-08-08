package com.projects.remainder.ui.mappers.tasks

import com.projects.domain.entity.EntityTask
import com.projects.remainder.ui.entity.tasks.TaskUiEntity
import com.projects.remainder.ui.mappers.utils.DateTimeUiMapper

class TaskUiMapper(
    private val dateTimeUiMapper: DateTimeUiMapper
) {
    fun map(task: EntityTask): TaskUiEntity {
        return TaskUiEntity(
            task.id,
            task.title,
            dateTimeUiMapper.map(task.dateTime)
        )
    }

    fun map(tasks: List<EntityTask>?): List<TaskUiEntity>? {
        return tasks?.map { map(it) }
    }
}