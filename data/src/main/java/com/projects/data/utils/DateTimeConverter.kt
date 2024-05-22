package com.projects.data.utils

import android.util.Log
import com.google.gson.JsonParseException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {

    @Throws(JsonParseException::class)
    fun fromUtcToLocal(date: String?, format: String) : Date? {
        val formatter = SimpleDateFormat(format)
        formatter.timeZone = TimeZone.getTimeZone(Calendar.getInstance().timeZone.id)
        return try {
            formatter.parse(date)
        } catch (exp: ParseException) {
            Log.e("Failed to parse Date:", exp.message, exp.cause)
            null
        }
    }

    // Constants.Formats.DATE_NETWORK
    fun fromLocalToUtc(date: Date, format: String): Date {
        val sdf = SimpleDateFormat(format)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return Date(sdf.format(date))
    }

}