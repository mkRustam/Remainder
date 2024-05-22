package com.projects.remainder.utils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ManagerNotification @Inject constructor(
    private val notificationManager: NotificationManager,
    @ApplicationContext private val appContext: Context
) {

    fun createNotification(
        channelId: String,
        title: String,
        content: String,
        @DrawableRes image: Int,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        intent: PendingIntent? = null
    ): Notification {
        return NotificationCompat.Builder(appContext, channelId)
            .setSmallIcon(image)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(priority)
            .setContentIntent(intent)
            .build()
    }

    fun showNotification(notificationId: Int, notification: Notification) {
        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(
        id: String,
        name: String,
        descr: String,
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT
    ) {
        val channel = NotificationChannel(id, name, importance).apply {
            description = descr
        }
        notificationManager.createNotificationChannel(channel)
    }
}