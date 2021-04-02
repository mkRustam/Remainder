package ru.mkr.data.repository.tasks.api.entity

import ru.mkr.data.network.entity.EntityApiResponse
import java.util.*

data class EntityApiTask(
    var _id: String?,
    var title: String,
    var dateTime: Date
): EntityApiResponse()