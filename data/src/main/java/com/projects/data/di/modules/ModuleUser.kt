package com.projects.data.di.modules

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.projects.data.modules.auth.mapper.MapperFirebaseUser
import com.projects.data.modules.auth.RepositoryUser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleUser {

    @Provides
    @Singleton
    fun provideRepositoryUser(firebaseAuth: FirebaseAuth,
                              mapperFirebaseUser: MapperFirebaseUser
    ) = RepositoryUser(firebaseAuth, mapperFirebaseUser)

    @Provides
    @Singleton
    fun provideMapperFirebaseUser(): MapperFirebaseUser = MapperFirebaseUser()

}