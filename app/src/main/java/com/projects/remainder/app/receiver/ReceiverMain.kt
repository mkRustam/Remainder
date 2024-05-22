package com.projects.remainder.app.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityTask
import com.projects.remainder.Constants
import com.projects.remainder.R
import com.projects.remainder.ui.activity.ActivityAlarm
import com.projects.remainder.utils.notification.ManagerNotification
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ReceiverMain : BroadcastReceiver() {

    @Inject lateinit var managerNotification: ManagerNotification

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Constants.Actions.ACTION_ALARM -> actionOnAlarm(context, intent)
        }
    }

    private fun actionOnAlarm(context:Context, intent: Intent) {
        val task = intent.getSerializableExtra(EXTRA_KEY_TASK) as EntityTask
        val contentIntent = PendingIntent.getActivity(
            context, 0,
            Intent(context, ActivityAlarm::class.java), PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            managerNotification.createChannel(TASK_CHANNEL_ID, TASK_CHANNEL_NAME, TASK_CHANNEL_DESCR)
        }
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
        private const val TASK_CHANNEL_ID = "notification_channel_task_alarm"
        private const val TASK_CHANNEL_NAME = "Уведомление о событии"
        private const val TASK_CHANNEL_DESCR = "Используется для отображения момента события"


        private const val EXTRA_KEY_TASK: String = "key_task"

        fun createAlarmIntent(context: Context, task: EntityTask): Intent {
            return Intent(context, ReceiverMain::class.java).apply {
                action = Constants.Actions.ACTION_ALARM
                putExtra(EXTRA_KEY_TASK, task)
            }
        }
    }
}