package com.projects.remainder.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import com.projects.remainder.ui.navigation.*
import com.projects.remainder.ui.screens.alarm.NavigationAlarm
import com.projects.remainder.ui.screens.auth.login.NavigationAuth
import com.projects.remainder.ui.screens.auth.register.NavigationAuthRegister
import com.projects.remainder.ui.screens.profile.NavigationProfile
import com.projects.remainder.ui.screens.splash.NavigationSplash
import com.projects.remainder.ui.screens.tasks.add.NavigationTaskAdd
import com.projects.remainder.ui.screens.tasks.detail.NavigationTaskDetail
import com.projects.remainder.ui.screens.tasks.list.NavigationTasks
import com.projects.remainder.ui.screens.tasks.update.NavigationTaskUpdate
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
interface NavigationBinds {

    @Binds
    fun bindNavigationAlarm(navigation: NavigationAlarmImpl): NavigationAlarm

    @Binds
    fun bindNavigationAuth(navigation: NavigationAuthImpl): NavigationAuth

    @Binds
    fun bindNavigationAuthRegister(navigation: NavigationAuthRegisterImpl): NavigationAuthRegister

    @Binds
    fun bindNavigationProfile(navigation: NavigationProfileImpl) : NavigationProfile

    @Binds
    fun bindNavigationSplash(navigation: NavigationSplashImpl): NavigationSplash

    @Binds
    fun bindNavigationTaskAdd(navigation: NavigationTaskAddImpl): NavigationTaskAdd

    @Binds
    fun bindNavigationTaskDetail(navigation: NavigationTaskDetailImpl): NavigationTaskDetail

    @Binds
    fun bindNavigationTasks(navigation: NavigationTasksImpl): NavigationTasks

    @Binds
    fun bindNavigationTaskUpdate(navigation: NavigationTaskUpdateImpl): NavigationTaskUpdate
}

@Module
@InstallIn(SingletonComponent::class)
class NavigationProvides {

    @Provides
    @Singleton
    fun provideRouter(): Router = Router()

    @Provides
    @Singleton
    fun provideRouterMainScreen(): RouterMainScreen = RouterMainScreen()
}