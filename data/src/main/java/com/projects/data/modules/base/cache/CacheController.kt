package com.projects.data.modules.base.cache

import com.projects.data.database.entity.EntityDb

class CacheController(private val cacheStrategy: CacheStrategy) {

    fun isRelevant(data: Any) : Boolean {
        return if(data is List<*>) isRelevant(cacheStrategy, data as List<EntityDb>)
        else isRelevant(cacheStrategy, data as EntityDb)
    }

    private fun isRelevant(cacheStrategy: CacheStrategy, dataList: List<EntityDb>): Boolean {
        for(data in dataList)
            if(!isRelevant(cacheStrategy, data))
                return false
        return true
    }

    private fun isRelevant(cacheStrategy: CacheStrategy, data: EntityDb): Boolean {
        return cacheStrategy.isRelevant(data)
    }
}