package ru.mkr.domain.utils

import ru.mkr.domain.entity.EntityTask

interface ITaskAlarmManager {
    fun schedule(task: EntityTask)
    fun cancel(task: EntityTask)
}