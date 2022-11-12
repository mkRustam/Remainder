package ru.mkr.remainder.utils.alarm

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.utils.ITaskAlarmManager
import ru.mkr.remainder.Constants
import ru.mkr.remainder.app.receiver.ReceiverMain
import ru.mkr.remainder.utils.base.ManagerAlarm
import javax.inject.Inject

class ManagerAlarmTask @Inject constructor(
    @ApplicationContext private var context: Context,
    private var managerAlarm: ManagerAlarm,
    private var gson: Gson
): ITaskAlarmManager {

    override fun schedule(task: EntityTask) {
        managerAlarm.scheduleExact(task.dateTime.time, createTaskIntent(task))
    }

    override fun cancel(task: EntityTask) {
        managerAlarm.cancel(createTaskIntent(task))
    }

    private fun createTaskIntent(task: EntityTask): PendingIntent {
        val reqCode = task.id.hashCode()
        val intent = Intent(context, ReceiverMain::class.java)
        intent.action = Constants.Actions.ACTION_ALARM
        intent.putExtra(ReceiverMain.TASK, gson.toJson(task))

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