package com.projects.remainder.ui.mappers.utils

import com.projects.utils.Constants.Formats.DATE_TIME_DEFAULT
import java.text.SimpleDateFormat
import java.util.Date

class DateTimeUiMapper {

    private val spf: SimpleDateFormat by lazy {
        SimpleDateFormat(DATE_TIME_DEFAULT)
    }

    fun map(date: Date): String {
        return spf.format(date)
    }

    fun map(string: String): Date {
        return spf.parse(string)
    }
}