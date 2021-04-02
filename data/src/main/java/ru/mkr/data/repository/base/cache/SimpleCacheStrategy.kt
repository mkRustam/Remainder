package ru.mkr.data.repository.base.cache

import ru.mkr.data.Constants
import ru.mkr.data.database.entity.EntityDb
import java.util.*

class SimpleCacheStrategy(@CachePeriod var cachePeriod: Long) : CacheStrategy {

    override fun isRelevant(data: EntityDb): Boolean {
        val cachedAt = data._timestamp
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone(Constants.App.DB_TIME_ZONE)).timeInMillis
        if(cachedAt != null) return currentTime < cachedAt + cachePeriod
        else return true
    }

    override fun isRelevant(data: List<EntityDb>): Boolean {
        for (item in data) if(!isRelevant(item)) return false
        return true
    }
}