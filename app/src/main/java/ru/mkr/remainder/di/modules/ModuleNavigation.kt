package ru.mkr.remainder.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import ru.mkr.remainder.ui.navigation.*
import ru.mkr.remainder.ui.screens.alarm.NavigationAlarm
import ru.mkr.remainder.ui.screens.auth.login.NavigationAuth
import ru.mkr.remainder.ui.screens.auth.register.NavigationAuthRegister
import ru.mkr.remainder.ui.screens.profile.NavigationProfile
import ru.mkr.remainder.ui.screens.splash.NavigationSplash
import ru.mkr.remainder.ui.screens.tasks.add.NavigationTaskAdd
import ru.mkr.remainder.ui.screens.tasks.detail.NavigationTaskDetail
import ru.mkr.remainder.ui.screens.tasks.list.NavigationTasks
import ru.mkr.remainder.ui.screens.tasks.update.NavigationTaskUpdate
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