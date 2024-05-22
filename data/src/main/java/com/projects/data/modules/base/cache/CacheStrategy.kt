package com.projects.data.modules.base.cache

import com.projects.data.database.entity.EntityDb

interface CacheStrategy {
    fun isRelevant(data: EntityDb): Boolean
    fun isRelevant(data: List<EntityDb>): Boolean
}