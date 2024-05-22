package com.projects.remainder.utils.base

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build
import javax.inject.Inject

class ManagerAlarm @Inject constructor(
    private var alarmManager: AlarmManager
) {

    fun scheduleExact(timeInMs: Long, pendingIntent: PendingIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMs, pendingIntent)
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMs, pendingIntent)
        }
    }

    fun cancel(pendingIntent: PendingIntent) {
        alarmManager.cancel(pendingIntent)
    }

}