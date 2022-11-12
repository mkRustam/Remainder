package ru.mkr.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mkr.data.repository.tasks.database.DaoTasks
import ru.mkr.data.repository.tasks.database.EntityDbTask

@Database(entities = [EntityDbTask::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): DaoTasks
}