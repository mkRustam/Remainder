package com.projects.data.modules.base.cache

import com.projects.data.Constants
import com.projects.data.database.entity.EntityDb
import java.util.*

class SimpleCacheStrategy(@CachePeriod var cachePeriod: Long) : CacheStrategy {

    override fun isRelevant(data: EntityDb): Boolean {
        val cachedAt = data.timeStamp
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone(Constants.App.DB_TIME_ZONE)).timeInMillis
        if(cachedAt != null) return currentTime < cachedAt + cachePeriod
        else return true
    }

    override fun isRelevant(data: List<EntityDb>): Boolean {
        for (item in data) if(!isRelevant(item)) return false
        return true
    }
}