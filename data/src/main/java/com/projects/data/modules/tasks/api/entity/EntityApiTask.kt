package com.projects.data.modules.tasks.api.entity

import com.projects.data.network.entity.EntityApiResponse
import java.util.*

data class EntityApiTask(
    var _id: String?,
    var title: String,
    var dateTime: Date
): EntityApiResponse()