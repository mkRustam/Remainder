package ru.mkr.data.repository.tasks.request

import ru.mkr.data.repository.base.LoadRequest

class TaskRequest(
    var taskId: String
) : LoadRequest()