package ru.mkr.domain.di.module.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.usecase.tasks.UseCaseTaskCreate
import ru.mkr.domain.usecase.tasks.UseCaseTaskDetail
import ru.mkr.domain.usecase.tasks.UseCaseTaskUpdate
import ru.mkr.domain.usecase.tasks.UseCaseTasksLoad
import ru.mkr.domain.utils.annotations.IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
class ModuleUseCaseTasks {

    @Provides
    fun provideUseCaseTasksCreate(
        repositoryTasks: IRepositoryTasks,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseTaskCreate(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseTasksLoad(
        repositoryTasks: IRepositoryTasks,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseTasksLoad(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseTasksUpdate(
        repositoryTasks: IRepositoryTasks,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseTaskUpdate(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseTaskDetail(
        repositoryTasks: IRepositoryTasks,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseTaskDetail(repositoryTasks, dispatcher)

}