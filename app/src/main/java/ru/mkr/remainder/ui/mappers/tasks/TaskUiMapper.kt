package ru.mkr.remainder.ui.mappers.tasks

import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.ui.entity.tasks.TaskUiEntity
import ru.mkr.remainder.ui.utils.DateTimeHelper

class TaskUiMapper {
    fun map(task: EntityTask): TaskUiEntity {
        return TaskUiEntity(
            task.id,
            task.title,
            DateTimeHelper.formatDate(task.dateTime)
        )
    }

    fun map(tasks: List<EntityTask>?): List<TaskUiEntity>? {
        return tasks?.map { map(it) }
    }
}