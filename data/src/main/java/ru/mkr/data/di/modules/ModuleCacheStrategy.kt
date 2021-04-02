package ru.mkr.data.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mkr.data.repository.base.cache.*

@Module
@InstallIn(SingletonComponent::class)
class ModuleCacheStrategy {

    @Provides
    @CachePeriodShort
    fun bindSimpleShort() = SimpleCacheStrategy(CachePeriod.EXPIRE_FAST)

    @Provides
    @CachePeriodDefault
    fun bindSimpleDefault() = SimpleCacheStrategy(CachePeriod.EXPIRE_NORMAL)

    @Provides
    @CachePeriodLong
    fun bindSimpleLong() = SimpleCacheStrategy(CachePeriod.EXPIRE_LONG)

    @Provides
    @CachePeriodInfinity
    fun bindSimpleInfinity() = SimpleCacheStrategy(CachePeriod.EXPIRE_INFINITY)
}