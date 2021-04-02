package ru.mkr.domain.di.module.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.repository.IRepositoryUser
import ru.mkr.domain.usecase.auth.UseCaseUserAutologin
import ru.mkr.domain.usecase.auth.UseCaseUserLogin
import ru.mkr.domain.usecase.auth.UseCaseUserLogout
import ru.mkr.domain.usecase.auth.UseCaseUserRegister
import ru.mkr.domain.utils.annotations.IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
class ModuleUseCaseUser {

    @Provides
    fun provideUseCaseUserAutologin(
        repositoryTasks: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserAutologin(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseUserLogin(
        repositoryTasks: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserLogin(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseUserRegister(
        repositoryTasks: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserRegister(repositoryTasks, dispatcher)

    @Provides
    fun provideUseCaseUserLogout(
        repositoryTasks: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserLogout(repositoryTasks, dispatcher)
}