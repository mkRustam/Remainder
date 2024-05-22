package com.projects.remainder.di.modules

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.PowerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ModuleSystemServices {

    @Provides
    @Singleton
    fun provideManagerPackage(@ApplicationContext appContext: Context): PackageManager =
        appContext.packageManager

    @Provides
    @Singleton
    fun provideSystemServiceAlarm(@ApplicationContext appContext: Context) =
        appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Provides
    @Singleton
    fun provideSystemServicePower(@ApplicationContext appContext: Context) =
        appContext.getSystemService(Context.POWER_SERVICE) as PowerManager

    @Provides
    @Singleton
    fun provideSystemNotificationManager(@ApplicationContext appContext: Context) =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}