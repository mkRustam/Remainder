package com.projects.domain.di.module.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.repository.IRepositoryUser
import com.projects.domain.usecase.auth.UseCaseUserAutologin
import com.projects.domain.usecase.auth.UseCaseUserLogin
import com.projects.domain.usecase.auth.UseCaseUserLogout
import com.projects.domain.usecase.auth.UseCaseUserRegister
import com.projects.domain.utils.annotations.IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
class ModuleUseCaseUser {

    @Provides
    fun provideUseCaseUserAutologin(
        repositoryUser: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserAutologin(repositoryUser, dispatcher)

    @Provides
    fun provideUseCaseUserLogin(
        repositoryUser: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserLogin(repositoryUser, dispatcher)

    @Provides
    fun provideUseCaseUserRegister(
        repositoryUser: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserRegister(repositoryUser, dispatcher)

    @Provides
    fun provideUseCaseUserLogout(
        repositoryUser: IRepositoryUser,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = UseCaseUserLogout(repositoryUser, dispatcher)
}