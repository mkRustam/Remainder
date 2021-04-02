package ru.mkr.data.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mkr.data.repository.auth.RepositoryUser
import ru.mkr.data.repository.tasks.RepositoryTasks
import ru.mkr.domain.repository.IRepositoryTasks
import ru.mkr.domain.repository.IRepositoryUser

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleRepository {

    @Binds
    abstract fun bindRepositoryTasks(repositoryTasks: RepositoryTasks): IRepositoryTasks

    @Binds
    abstract fun bindRepositoryUser(repositoryUser: RepositoryUser): IRepositoryUser

}