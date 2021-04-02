package ru.mkr.data.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mkr.data.database.AppDatabase
import ru.mkr.data.repository.tasks.database.DaoTasks
import ru.mkr.data.repository.tasks.mapper.MapperTask
import ru.mkr.data.repository.tasks.mapper.MapperTasks
import ru.mkr.data.network.api.ApiRequests
import ru.mkr.data.repository.tasks.data_sources.LocalDataSourceTasks
import ru.mkr.data.repository.tasks.data_sources.RemoteDataSourceTasks
import ru.mkr.data.repository.tasks.RepositoryTasks
import ru.mkr.data.repository.tasks.strategy.*
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