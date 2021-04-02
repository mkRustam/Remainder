package ru.mkr.remainder.ui.utils

import ru.mkr.remainder.Constants
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    @JvmStatic
    fun formatDate(date: Date?): String? {
        return date?.let {
            val format = Constants.Formats.DATE_TIME_DEFAULT
            val spf = SimpleDateFormat(format)
            return spf.format(date)
        }
    }

    @JvmStatic
    fun parseDate(text: String?): Date? {
        return text?.let {
            // TODO test
            val format = Constants.Formats.DATE_TIME_DEFAULT
            val spf = SimpleDateFormat(format)
            return spf.parse(text)
        }
    }
}