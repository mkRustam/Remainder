package com.projects.data.modules.tasks.request

import com.projects.data.modules.base.LoadRequest
import com.projects.data.modules.tasks.api.entity.EntityApiTask

class TaskAddRequest(
    val task: EntityApiTask
) : LoadRequest()