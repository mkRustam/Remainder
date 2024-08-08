package com.projects.remainder.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.projects.remainder.ui.mappers.tasks.TaskUiMapper
import com.projects.remainder.ui.mappers.utils.DateTimeUiMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleTasks {

    @Provides
    @Singleton
    fun provideDateTimeUiMapper() = DateTimeUiMapper()

    @Provides
    @Singleton
    fun provideTaskUiMapper(dateTimeUiMapper: DateTimeUiMapper) = TaskUiMapper(dateTimeUiMapper)

}