package ru.mkr.data.repository.tasks.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mkr.data.database.entity.EntityDb

@Entity(tableName = "tasks")
class EntityDbTask(
    @PrimaryKey val id: String,
    val title: String,
    val dateTime: Long,
    _timestamp: Long? = null
): EntityDb(_timestamp)