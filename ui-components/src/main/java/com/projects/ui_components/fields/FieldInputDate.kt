package com.projects.ui_components.fields

import android.content.Context
import android.util.AttributeSet
import com.projects.ui_components.dialogs.DialogDateSelector
import com.projects.ui_components.dialogs.DialogTimeSelector
import java.util.Calendar
import java.util.Date

class FieldInputDate : FieldInputBase<Date> {

    private var withTime = false
    private var selectedDate: Calendar = Calendar.getInstance()

    private var selectListener: ((Date) -> Unit)? = null
    private lateinit var formatterToText: (Date) -> String
    private lateinit var formatterFromText: (String) -> Date

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setSelectedDate(selectedDate: Date?) {
        if(selectedDate != null) this.selectedDate.timeInMillis = selectedDate.time
        updateFieldValue()
    }

    fun setFormatter(
        toText: (Date) -> String,
        fromText: (String) -> Date
    ) {
        this.formatterToText = toText
        this.formatterFromText = fromText
    }

    fun setSelectListener(selectListener: ((Date) -> Unit)?) {
        this.selectListener = selectListener
    }

    fun setWithTime(): FieldInputDate {
        this.withTime = true
        return this
    }

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
        return if(!text.isNullOrEmpty()) formatterFromText(text) else null
    }

    private fun showDatePicker(finishListener: (Calendar) -> Unit) {
        DialogDateSelector.Builder(context)
            .resultListener(finishListener)
            .initDate(selectedDate)
            .build()
            .show()
    }

    private fun showTimePicker(resultListener: (Calendar) -> Unit) {
        DialogTimeSelector.Builder(context)
            .initTime(selectedDate)
            .resultListener(resultListener)
            .build()
            .show()
    }

    private fun updateFieldValue() {
        setValue(formatterToText(this.selectedDate.time), this.selectedDate.time)
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
}