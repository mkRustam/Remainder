package ru.mkr.data.repository.tasks.mapper

import ru.mkr.data.repository.tasks.database.EntityDbTask
import ru.mkr.data.mapper.IMapper
import ru.mkr.data.repository.tasks.api.entity.EntityApiTask
import ru.mkr.domain.entity.EntityTask
import java.util.*

class MapperTask : IMapper<EntityDbTask, EntityApiTask, EntityTask> {

    override fun fromDbToDomain(data: EntityDbTask): EntityTask {
        return EntityTask(data.id, data.title, Date(data.dateTime))
    }

    override fun fromApiToDb(data: EntityApiTask): EntityDbTask {
        return EntityDbTask(data._id!!, data.title, data.dateTime.time)
    }
}