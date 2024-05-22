package com.projects.domain.entity

import java.util.*

data class EntityTask(
    var id: String?,
    var title: String,
    var dateTime: Date
): Entity()