package com.projects.remainder.utils.base

import android.app.AlarmManager
import android.app.PendingIntent
import javax.inject.Inject

class ManagerAlarm @Inject constructor(
    private var alarmManager: AlarmManager
) {

    fun scheduleExact(timeInMs: Long, pendingIntent: PendingIntent) {
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMs, pendingIntent)
    }

    fun cancel(pendingIntent: PendingIntent) {
        alarmManager.cancel(pendingIntent)
    }

}