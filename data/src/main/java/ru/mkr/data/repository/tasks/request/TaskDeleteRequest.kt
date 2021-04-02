package ru.mkr.data.repository.tasks.request

import ru.mkr.data.repository.base.LoadRequest

class TaskDeleteRequest(
    val taskId: String
) : LoadRequest()