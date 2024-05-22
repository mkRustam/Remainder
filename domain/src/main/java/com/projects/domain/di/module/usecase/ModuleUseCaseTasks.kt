package com.projects.domain.di.module.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.repository.IRepositoryTasks
import com.projects.domain.usecase.tasks.UseCaseTaskCreate
import com.projects.domain.usecase.tasks.UseCaseTaskDetail
import com.projects.domain.usecase.tasks.UseCaseTaskUpdate
import com.projects.domain.usecase.tasks.UseCaseTasksLoad
import com.projects.domain.utils.annotations.IoDispatcher

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