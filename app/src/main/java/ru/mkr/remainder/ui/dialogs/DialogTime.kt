package ru.mkr.remainder.ui.dialogs

import android.content.Context
import android.widget.TextView
import android.widget.TimePicker
import ru.mkr.remainder.R
import java.util.*


class DialogTime(context: Context) : BaseDialog(context) {

    private var finishListener: ((Calendar) -> Unit)? = null

    //region ******************** OPTIONS **********************************************************

    fun setFinishListener(finishListener: (Calendar) -> Unit): DialogTime {
        this.finishListener = finishListener
        return this
    }

    //endregion OPTIONS

    //region ******************** OVERRIDE *********************************************************

    override fun getLayoutId(): Int = R.layout.dialog_time

    override fun initDialog() {
        val timePicker = findView<TimePicker>(R.id.time_picker)
        findView<TextView>(R.id.button_ok).setOnClickListener {
            val calendarTime = Calendar.getInstance()
            calendarTime.set(Calendar.HOUR_OF_DAY, timePicker.currentHour)
            calendarTime.set(Calendar.MINUTE, timePicker.currentMinute)
            finishListener?.invoke(calendarTime)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener {
            hide()
        }
    }

    //endregion OVERRIDE

}