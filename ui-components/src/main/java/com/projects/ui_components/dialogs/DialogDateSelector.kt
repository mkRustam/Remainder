package com.projects.ui_components.dialogs

import android.content.Context
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.projects.ui_components.R
import java.util.*


class DialogDateSelector private constructor(context: Context) : BaseDialog(context), OnDateSelectedListener {

    private var resultListener: ((Calendar) -> Unit)? = null
    private var selectedDate: Calendar? = null
    private var initDate: Calendar? = null

    override fun getLayoutId(): Int = R.layout.dialog_calendar

    override fun initDialog() {
        val calendarView: MaterialCalendarView = findView(R.id.calendarView)
        calendarView.setOnDateChangedListener(this)
        calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_SINGLE

        findView<TextView>(R.id.button_ok).setOnClickListener {
            if(selectedDate != null) resultListener?.invoke(selectedDate!!)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener {
            hide()
        }

        initDate?.let { date ->
            this.selectedDate = date

            calendarView.setDateSelected(date.toCalendarDay(), true)
            calendarView.currentDate = date.toCalendarDay()
        }
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        selectedDate = date.date.toCalendar()
    }

    private fun org.threeten.bp.LocalDate.toCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = this.year
        calendar[Calendar.MONTH] = this.monthValue - 1
        calendar[Calendar.DAY_OF_MONTH] = this.dayOfMonth
        return calendar
    }

    private fun Calendar.toCalendarDay(): CalendarDay {
        return CalendarDay.from(this.get(Calendar.YEAR), this.get(Calendar.MONTH), this.get(Calendar.DAY_OF_MONTH))
    }

    public class Builder(
        private val context: Context,
    ) {
        private var initDate: Calendar? = null
        private var resultListener: ((Calendar) -> Unit)? = null

        fun initDate(date: Calendar): Builder {
            this.initDate = date
            return this
        }

        fun resultListener(resultListener: (Calendar) -> Unit): Builder {
            this.resultListener = resultListener
            return this
        }

        fun build(): DialogDateSelector {
            val instance = DialogDateSelector(context)
            instance.initDate = initDate
            instance.resultListener = resultListener
            instance.initDialog()
            return instance
        }
    }
}