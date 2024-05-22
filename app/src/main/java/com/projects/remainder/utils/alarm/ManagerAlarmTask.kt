package com.projects.remainder.utils.alarm

import android.app.PendingIntent
import android.content.Context
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import com.projects.domain.entity.EntityTask
import com.projects.domain.utils.ITaskAlarmManager
import com.projects.remainder.app.receiver.ReceiverMain
import com.projects.remainder.utils.base.ManagerAlarm
import javax.inject.Inject

class ManagerAlarmTask @Inject constructor(
    @ApplicationContext private var context: Context,
    private var managerAlarm: ManagerAlarm
): ITaskAlarmManager {

    override fun schedule(task: EntityTask) {
        managerAlarm.scheduleExact(task.dateTime.time, createTaskIntent(task))
    }

    override fun cancel(task: EntityTask) {
        managerAlarm.cancel(createTaskIntent(task))
    }

    private fun createTaskIntent(task: EntityTask): PendingIntent {
        val reqCode = task.id.hashCode()
        val intent = ReceiverMain.createAlarmIntent(context, task)
        return PendingIntent.getBroadcast(context, reqCode, intent, getFlagsForTaskCreateIntent())
    }

    private fun getFlagsForTaskCreateIntent(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    }
}