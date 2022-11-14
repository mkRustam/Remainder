package ru.mkr.remainder.app.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.Constants
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.activity.ActivityAlarm
import ru.mkr.remainder.utils.notification.ManagerNotification
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ReceiverMain : BroadcastReceiver() {

    @Inject public lateinit var gson: Gson
    @Inject public lateinit var managerNotification: ManagerNotification

    private val TASK_CHANNEL_ID = "notification_channel_task_alarm"
    private val TASK_CHANNEL_NAME = "Уведомление о событии"
    private val TASK_CHANNEL_DESCR = "Используется для отображения момента события"

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Constants.Actions.ACTION_ALARM -> actionOnAlarm(context, intent)
        }
    }

    private fun actionOnAlarm(context:Context, intent: Intent) {
        val task = gson.fromJson(intent.getStringExtra(TASK), EntityTask::class.java)
        val contentIntent = PendingIntent.getActivity(
            context, 0,
            Intent(context, ActivityAlarm::class.java), PendingIntent.FLAG_UPDATE_CURRENT
        )
        managerNotification.createChannel(TASK_CHANNEL_ID, TASK_CHANNEL_NAME, TASK_CHANNEL_DESCR)
        val notification = managerNotification.createNotification(
            TASK_CHANNEL_ID,
            task.title,
            task.dateTime.toLocaleString(),
            R.drawable.ic_launcher_foreground,
            intent = contentIntent)
        val notificationId = UUID.randomUUID().hashCode()
        managerNotification.showNotification(notificationId, notification)
    }

    companion object {
        public const val TASK = "task"
    }
}