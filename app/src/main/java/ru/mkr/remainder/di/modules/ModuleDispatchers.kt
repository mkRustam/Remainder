package ru.mkr.remainder.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.mkr.domain.utils.annotations.DefaultDispatcher
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.domain.utils.annotations.MainDispatcher

@InstallIn(SingletonComponent::class)
@Module
class ModuleDispatchers {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}