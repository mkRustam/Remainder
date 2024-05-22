package com.projects.remainder.ui.mappers.tasks

import com.projects.domain.entity.EntityTask
import com.projects.remainder.ui.entity.tasks.TaskUiEntity
import com.projects.ui_components.utils.toDefaultString

class TaskUiMapper {
    fun map(task: EntityTask): TaskUiEntity {
        return TaskUiEntity(
            task.id,
            task.title,
            task.dateTime.toDefaultString()
        )
    }

    fun map(tasks: List<EntityTask>?): List<TaskUiEntity>? {
        return tasks?.map { map(it) }
    }
}