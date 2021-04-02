package ru.mkr.data.repository.tasks.request

import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask

class TaskUpdateRequest(
    val taskId: String,
    val task: EntityApiTask
) : LoadRequest()