package com.projects.domain.utils

import com.projects.domain.entity.EntityTask

interface ITaskAlarmManager {
    fun schedule(task: EntityTask)
    fun cancel(task: EntityTask)
}