package ru.mkr.data.repository.base.cache

import ru.mkr.data.database.entity.EntityDb

interface CacheStrategy {
    fun isRelevant(data: EntityDb): Boolean
    fun isRelevant(data: List<EntityDb>): Boolean
}