package ru.mkr.remainder.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.Constants
import ru.mkr.remainder.R
import javax.inject.Inject

@AndroidEntryPoint
class ReceiverMain : BroadcastReceiver() {

    @Inject public lateinit var gson: Gson

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Constants.Actions.ACTION_ALARM -> actionOnAlarm(context, intent)
        }
    }

    private fun actionOnAlarm(context:Context, intent: Intent) {
        val task = gson.fromJson(intent.getStringExtra(Constants.Extras.EXTRA_TASK), EntityTask::class.java)
        Log.d("Alarmer", "Task: ${task.id}")
        if(task != null) {
//            val args = FragmentAlarmArgs(task).toBundle()
//            val navPendingIntent = NavDeepLinkBuilder(context)
//                .setGraph(R.navigation.deeplinks_navigation)
//                .setDestination(R.id.fragmentAlarm)
//                .setArguments(args)
//                .createPendingIntent()
//            navPendingIntent.send()
        }
    }
}