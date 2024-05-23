package com.projects.ui_components.dialogs

import android.content.Context
import android.widget.TextView
import android.widget.TimePicker
import com.projects.ui_components.R
import com.projects.ui_components.utils.toCalendar
import java.util.*


class DialogTime(context: Context) : BaseDialog(context) {

    private var finishListener: ((Calendar) -> Unit)? = null
    private var selectedTime: Calendar = Calendar.getInstance()
    private var timeToInit: Date? = null

    fun setFinishListener(finishListener: (Calendar) -> Unit): DialogTime {
        this.finishListener = finishListener
        return this
    }

    fun setInitTime(time: Date): DialogTime {
        this.selectedTime = time.toCalendar()
        this.timeToInit = time
        return this
    }

    override fun getLayoutId(): Int = R.layout.dialog_time

    override fun initDialog() {
        val timePicker = findView<TimePicker>(R.id.time_picker)
        timePicker.setIs24HourView(true)

        findView<TextView>(R.id.button_ok).setOnClickListener {
            selectedTime.set(Calendar.HOUR_OF_DAY, timePicker.currentHour)
            selectedTime.set(Calendar.MINUTE, timePicker.currentMinute)
            finishListener?.invoke(selectedTime)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener {
            hide()
        }

        timeToInit?.let { time ->
            timePicker.currentHour = time.hours
            timePicker.currentMinute = time.minutes
        }
    }
}