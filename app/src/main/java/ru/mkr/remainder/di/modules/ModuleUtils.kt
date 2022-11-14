package ru.mkr.remainder.di.modules

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mkr.domain.utils.ITaskAlarmManager
import ru.mkr.remainder.utils.activity_result_contracts.ContractBatteryOptimizationIgnore
import ru.mkr.remainder.utils.alarm.ManagerAlarmTask
import ru.mkr.remainder.utils.base.ManagerAlarm
import ru.mkr.remainder.utils.base.ManagerPower
import ru.mkr.remainder.utils.notification.ManagerNotification
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleUtils {

    @Provides
    @Singleton
    fun provideManagerAlarmTask(
        @ApplicationContext appContext: Context,
        managerAlarm: ManagerAlarm,
        gson: Gson
    ): ITaskAlarmManager =
        ManagerAlarmTask(appContext, managerAlarm, gson)

    @Provides
    @Singleton
    fun provideManagerAlarm(alarmManager: AlarmManager) =
        ManagerAlarm(alarmManager)

    @Provides
    @Singleton
    fun provideManagerPower(powerManager: PowerManager) =
        ManagerPower(powerManager)

    @RequiresApi(Build.VERSION_CODES.M)
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