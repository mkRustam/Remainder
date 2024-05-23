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
import com.projects.domain.utils.ITaskAlarmManager
import com.projects.remainder.utils.activity_result_contracts.ContractBatteryOptimizationIgnore
import com.projects.remainder.utils.alarm.ManagerAlarmTask
import com.projects.remainder.utils.base.ManagerAlarm
import com.projects.remainder.utils.base.ManagerPower
import com.projects.remainder.utils.notification.ManagerNotification
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleUtils {

    @Provides
    @Singleton
    fun provideManagerAlarmTask(
        @ApplicationContext appContext: Context,
        managerAlarm: ManagerAlarm
    ): ITaskAlarmManager =
        ManagerAlarmTask(appContext, managerAlarm)

    @Provides
    @Singleton
    fun provideManagerAlarm(alarmManager: AlarmManager) =
        ManagerAlarm(alarmManager)

    @Provides
    @Singleton
    fun provideManagerPower(powerManager: PowerManager) =
        ManagerPower(powerManager)

    @Provides
    fun provideContractBatteryOptimization(
        powerManager: ManagerPower,
        packageManager: PackageManager
    ) =
        ContractBatteryOptimizationIgnore(powerManager, packageManager)

    @Provides
    @Singleton
    fun provideManagerNotification(
        notificationManager: NotificationManager,
        @ApplicationContext appContext: Context
    ) = ManagerNotification(notificationManager, appContext)
}