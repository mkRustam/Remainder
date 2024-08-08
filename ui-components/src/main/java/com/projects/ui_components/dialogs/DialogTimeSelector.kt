package com.projects.ui_components.dialogs

import android.content.Context
import android.widget.TextView
import android.widget.TimePicker
import com.projects.ui_components.R
import java.util.Calendar
import java.util.Date

class DialogTimeSelector private constructor(context: Context) : BaseDialog(context) {

    private var resultListener: ((Calendar) -> Unit)? = null
    private var selectedTime: Calendar = Calendar.getInstance()
    private var initTime: Calendar? = null

    override fun getLayoutId(): Int = R.layout.dialog_time

    override fun initDialog() {
        val timePicker = findView<TimePicker>(R.id.time_picker)
        timePicker.setIs24HourView(true)

        findView<TextView>(R.id.button_ok).setOnClickListener {
            selectedTime.set(Calendar.HOUR_OF_DAY, timePicker.currentHour)
            selectedTime.set(Calendar.MINUTE, timePicker.currentMinute)
            resultListener?.invoke(selectedTime)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener { hide() }

        initTime?.let { selectedTime ->
            this.selectedTime = selectedTime

            timePicker.currentHour = selectedTime.get(Calendar.HOUR_OF_DAY)
            timePicker.currentMinute = selectedTime.get(Calendar.MINUTE)
        }
    }

    public class Builder(
        private val context: Context,
    ) {
        private var initTime: Calendar? = null
        private var resultListener: ((Calendar) -> Unit)? = null

        fun initTime(time: Calendar): Builder {
            this.initTime = time
            return this
        }

        fun resultListener(resultListener: (Calendar) -> Unit): Builder {
            this.resultListener = resultListener
            return this
        }

        fun build(): DialogTimeSelector {
            val instance = DialogTimeSelector(context)
            instance.initTime = initTime
            instance.resultListener = resultListener
            instance.initDialog()
            return instance
        }
    }
}
