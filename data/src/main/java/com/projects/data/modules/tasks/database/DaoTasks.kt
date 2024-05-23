package com.projects.data.modules.tasks.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoTasks {
    @Query("SELECT * FROM tasks")
    fun observeAll(): Flow<List<EntityDbTask>>

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun observeById(id: String): Flow<EntityDbTask>

    @Deprecated(message = "Use insertOrUpdate", replaceWith = ReplaceWith("insertOrUpdate(task)"))
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: EntityDbTask)

    @Deprecated(message = "Use insertOrUpdate", replaceWith = ReplaceWith("insertOrUpdate(task)"))
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: EntityDbTask): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<EntityDbTask>)

    @Query("DELETE FROM tasks WHERE id=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Transaction
    suspend fun refresh(tasks: List<EntityDbTask>) {
        deleteAll()
        insertAll(tasks)
    }

    @Transaction
    suspend fun insertOrUpdate(task: EntityDbTask) {
        val updatedRows = update(task)
        if (updatedRows == 0) insert(task)
    }
}