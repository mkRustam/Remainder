package ru.mkr.data.repository.tasks.mapper

import ru.mkr.data.repository.tasks.database.EntityDbTask
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.data.mapper.IMapper
import ru.mkr.domain.entity.EntityTask
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