package ru.mkr.remainder.ui.views.fields

import android.content.Context
import android.util.AttributeSet
import ru.mkr.remainder.ui.dialogs.DialogCalendarSingle
import ru.mkr.remainder.ui.dialogs.DialogTime
import ru.mkr.remainder.ui.utils.DateTimeHelper
import java.util.*

class FieldInputDate : FieldInputBase<Date> {

    private var withTime = false
    private var selectedDate: Calendar = Calendar.getInstance()

    private var selectListener: ((Date) -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //region ******************** OPTIONS **********************************************************

    fun setSelectedDate(selectedDate: Date?) {
        if(selectedDate != null) this.selectedDate.timeInMillis = selectedDate.time
        updateFieldValue()
    }

    fun setSelectListener(selectListener: ((Date) -> Unit)?) {
        this.selectListener = selectListener
    }

    fun setWithTime(): FieldInputDate {
        this.withTime = true
        return this
    }

    //endregion OPTIONS

    //region ******************** OVERRIDE *********************************************************

    override fun initField() {
        setNoFocusable()

        setClickListener {
            showDatePicker { date ->
                if (withTime) {
                    showTimePicker { time ->
                        setDate(date)
                        setTime(time)
                        updateFieldValue()
                    }
                } else {
                    setDate(date)
                    updateFieldValue()
                }
            }
        }
    }

    override fun toValue(text: String?): Date? {
        return if(!text.isNullOrEmpty()) DateTimeHelper.parseDate(text) else null
    }

    //endregion OVERRIDE

    //region ******************** HELPERS **********************************************************

    private fun showDatePicker(finishListener: (Calendar) -> Unit) {
        DialogCalendarSingle(context)
            .setFinishListener(finishListener::invoke)
            .show()
    }

    private fun showTimePicker(finishListener: (Calendar) -> Unit) {
        DialogTime(context)
            .setFinishListener(finishListener::invoke)
            .show()
    }

    private fun updateFieldValue() {
        setValue(DateTimeHelper.formatDate(this.selectedDate.time), this.selectedDate.time)
    }

    private fun setDate(calendar: Calendar) {
        selectedDate.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        selectedDate.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        selectedDate.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
    }

    private fun setTime(calendar: Calendar) {
        selectedDate.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
        selectedDate.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
        selectedDate.set(Calendar.SECOND, 0)
    }

    //endregion HELPERS

}