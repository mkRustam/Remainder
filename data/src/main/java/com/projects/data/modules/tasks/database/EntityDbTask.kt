package com.projects.data.modules.tasks.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.projects.data.database.entity.EntityDb

@Entity(tableName = "tasks")
class EntityDbTask(
    @PrimaryKey val id: String,
    val title: String,
    val dateTime: Long,
    _timestamp: Long? = null
): EntityDb(_timestamp)