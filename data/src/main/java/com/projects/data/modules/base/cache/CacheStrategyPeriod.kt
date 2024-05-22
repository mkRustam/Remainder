package com.projects.data.modules.base.cache

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class CachePeriodShort

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class CachePeriodDefault

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class CachePeriodLong

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class CachePeriodInfinity