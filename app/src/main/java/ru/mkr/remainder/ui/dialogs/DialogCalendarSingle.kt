package ru.mkr.remainder.ui.dialogs

import android.content.Context
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import ru.mkr.remainder.R
import org.threeten.bp.LocalDate
import java.util.*


class DialogCalendarSingle(context: Context) : BaseDialog(context), OnDateSelectedListener {

    private var finishListener: ((Calendar) -> Unit)? = null
    private var selectedDate: Calendar? = null

    //region ******************** OPTIONS **********************************************************

    fun setFinishListener(finishListener: (Calendar) -> Unit): DialogCalendarSingle {
        this.finishListener = finishListener
        return this
    }

    //endregion OPTIONS

    //region ******************** OVERRIDES ********************************************************

    override fun getLayoutId(): Int = R.layout.dialog_calendar

    override fun initDialog() {
        val widget: MaterialCalendarView = findView(R.id.calendarView)
        widget.setOnDateChangedListener(this)

        findView<TextView>(R.id.button_ok).setOnClickListener {
            if(selectedDate != null) finishListener?.invoke(selectedDate!!)
            hide()
        }
        findView<TextView>(R.id.button_cancel).setOnClickListener {
            hide()
        }
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        selectedDate = dateFromLocalDate(date.date)
    }

    //endregion OVERRIDES

    //region ******************** HELPERS **********************************************************

    protected fun dateFromLocalDate(source: LocalDate): Calendar? {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = source.getYear()
        calendar[Calendar.MONTH] = source.getMonthValue() - 1
        calendar[Calendar.DAY_OF_MONTH] = source.getDayOfMonth()
        return calendar
    }

    //endregion HELPERS
}