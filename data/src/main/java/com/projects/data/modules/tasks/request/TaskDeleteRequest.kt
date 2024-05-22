package com.projects.data.modules.tasks.request

import com.projects.data.modules.base.LoadRequest

class TaskDeleteRequest(
    val taskId: String
) : LoadRequest()