package com.projects.ui_components.dialogs

import android.content.Context
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.projects.ui_components.R
import com.projects.ui_components.utils.toCalendar
import com.projects.ui_components.utils.toCalendarDay
import java.util.*


class DialogCalendarSingle(context: Context) : BaseDialog(context), OnDateSelectedListener {

    private var finishListener: ((Calendar) -> Unit)? = null
    private var selectedDate: Calendar? = null
    private var dateToInit: Date? = null

    //region ******************** OPTIONS **********************************************************

    fun setFinishListener(finishListener: (Calendar) -> Unit): DialogCalendarSingle {
        this.finishListener = finishListener
        return this
    }

    fun setInitDate(date: Date): DialogCalendarSingle {
        this.selectedDate = date.toCalendar()
        this.dateToInit = date
        return this
    }

    //endregion OPTIONS

    //region ******************** OVERRIDES ********************************************************

    override fun getLayoutId(): Int = R.layout.dialog_calendar

    override fun initDialog() {
        val calendarView: MaterialCalendarView = findView(R.id.calendarView)
        calendarView.setOnDateChangedListener(this)

        findView<TextView>(R.id.button_ok).setOnClickListener {
            if(selectedDate != null) finishListener?.invoke(selectedDate!!)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener {
            hide()
        }

        dateToInit?.let { date ->
            calendarView.currentDate = date.toCalendarDay()
        }
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        selectedDate = date.date.toCalendar()
    }

    //endregion OVERRIDES
}