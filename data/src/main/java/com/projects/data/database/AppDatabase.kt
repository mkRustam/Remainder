package com.projects.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projects.data.modules.tasks.database.DaoTasks
import com.projects.data.modules.tasks.database.EntityDbTask

@Database(entities = [EntityDbTask::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): DaoTasks
}