package com.projects.data.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.projects.data.modules.auth.RepositoryUser
import com.projects.data.modules.tasks.RepositoryTasks
import com.projects.domain.repository.IRepositoryTasks
import com.projects.domain.repository.IRepositoryUser

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleRepository {

    @Binds
    abstract fun bindRepositoryTasks(repositoryTasks: RepositoryTasks): IRepositoryTasks

    @Binds
    abstract fun bindRepositoryUser(repositoryUser: RepositoryUser): IRepositoryUser

}