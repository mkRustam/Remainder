package ru.mkr.remainder.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavDeepLinkBuilder
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.Constants
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.activity.ActivityMain
import ru.mkr.remainder.ui.screens.alarm.FragmentAlarm
import javax.inject.Inject

@AndroidEntryPoint
class ReceiverMain : BroadcastReceiver() {

    @Inject public lateinit var gson: Gson

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Constants.Actions.ACTION_ALARM -> actionOnAlarm(context.applicationContext, intent)
        }
    }

    private fun actionOnAlarm(context:Context, intent: Intent) {
        val task = gson.fromJson(intent.getStringExtra(TASK), EntityTask::class.java)
        Log.d("Alarmer", "Task: $task")
        if(task != null) {
            // TODO Open fragment with task details
        }
    }

    companion object {
        public const val TASK = "task"
    }
}