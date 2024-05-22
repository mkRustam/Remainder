package com.projects.data.modules.tasks.mapper

import com.projects.data.modules.tasks.database.EntityDbTask
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.data.mapper.IMapper
import com.projects.domain.entity.EntityTask
import kotlin.collections.List

class MapperTasks : IMapper<List<EntityDbTask>, List<EntityApiTask>, List<EntityTask>> {

    private var mapperTask = MapperTask()

    override fun fromDbToDomain(data: List<EntityDbTask>): List<EntityTask> {
        return data.map { mapperTask.fromDbToDomain(it) }
    }

    override fun fromApiToDb(data: List<EntityApiTask>): List<EntityDbTask> {
        return data.map { mapperTask.fromApiToDb(it) }
    }
}