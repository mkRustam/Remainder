package com.projects.ui_components.utils

import com.prolificinteractive.materialcalendarview.CalendarDay
import org.threeten.bp.LocalDate
import com.projects.utils.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.toCalendar(): Calendar {
    val c = Calendar.getInstance()
    c.time = this
    return c
}

fun LocalDate.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar[Calendar.YEAR] = this.year
    calendar[Calendar.MONTH] = this.monthValue - 1
    calendar[Calendar.DAY_OF_MONTH] = this.dayOfMonth
    return calendar
}

fun Date.toCalendarDay(): CalendarDay {
    return CalendarDay.from(this.year, this.month, this.day)
}

fun Date?.toDefaultString(): String? {
    return this?.let {
        val format = Constants.Formats.DATE_TIME_DEFAULT
        val spf = SimpleDateFormat(format)
        return spf.format(this)
    }
}

fun String?.toDefaultDate(): Date? {
    return this?.let {
        // TODO test
        val format = Constants.Formats.DATE_TIME_DEFAULT
        val spf = SimpleDateFormat(format)
        return spf.parse(this)
    }
}