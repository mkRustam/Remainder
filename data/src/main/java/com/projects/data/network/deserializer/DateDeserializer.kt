package com.projects.data.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.projects.data.Constants
import com.projects.data.utils.DateTimeConverter
import java.lang.reflect.Type
import java.util.*
import kotlin.jvm.Throws

class DateDeserializer : JsonDeserializer<Date?> {
    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, arg1: Type, arg2: JsonDeserializationContext): Date? {
        return DateTimeConverter.fromUtcToLocal(element.asString, Constants.Formats.DATE_NETWORK)
    }
}