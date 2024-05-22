package com.projects.data.modules.tasks.mapper

import com.projects.data.modules.tasks.database.EntityDbTask
import com.projects.data.mapper.IMapper
import com.projects.data.modules.tasks.api.entity.EntityApiTask
import com.projects.domain.entity.EntityTask
import java.util.*

class MapperTask : IMapper<EntityDbTask, EntityApiTask, EntityTask> {

    override fun fromDbToDomain(data: EntityDbTask): EntityTask {
        return EntityTask(data.id, data.title, Date(data.dateTime))
    }

    override fun fromApiToDb(data: EntityApiTask): EntityDbTask {
        return EntityDbTask(data._id!!, data.title, data.dateTime.time)
    }
}