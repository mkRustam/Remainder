package com.projects.data.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.projects.data.database.AppDatabase
import com.projects.data.modules.tasks.database.DaoTasks
import com.projects.data.modules.tasks.mapper.MapperTask
import com.projects.data.modules.tasks.mapper.MapperTasks
import com.projects.data.network.api.ApiRequests
import com.projects.data.modules.tasks.data_sources.LocalDataSourceTasks
import com.projects.data.modules.tasks.data_sources.RemoteDataSourceTasks
import com.projects.data.modules.tasks.RepositoryTasks
import com.projects.data.modules.tasks.strategy.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleTasks {

    @Provides
    @Singleton
    fun provideTasksDao(database: AppDatabase) = database.tasksDao();

    @Provides
    @Singleton
    fun provideTasksRemoteDataSource(retrofitApi: ApiRequests) = RemoteDataSourceTasks(retrofitApi)

    @Provides
    @Singleton
    fun provideTasksLocalDataSource(daoTasks: DaoTasks) = LocalDataSourceTasks(daoTasks)

    @Provides
    @Singleton
    fun provideTasksRepository(
        taskListLoadStrategy: TaskListLoadStrategy,
        taskLoadStrategy: TaskLoadStrategy,
        taskAddStrategy: TaskAddStrategy,
        taskDeleteStrategy: TaskDeleteStrategy,
        taskUpdateStrategy: TaskUpdateStrategy
    ) = RepositoryTasks(
        taskListLoadStrategy,
        taskLoadStrategy,
        taskAddStrategy,
        taskDeleteStrategy,
        taskUpdateStrategy
    )

    @Provides
    @Singleton
    fun provideMapperTasks() = MapperTasks()

    @Provides
    @Singleton
    fun provideMapperTask() = MapperTask()

}