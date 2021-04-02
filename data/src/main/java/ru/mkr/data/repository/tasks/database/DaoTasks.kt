package ru.mkr.data.repository.tasks.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoTasks {
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<EntityDbTask>>

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun get(id: String): Flow<EntityDbTask>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: EntityDbTask)

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
}