package com.projects.data.modules.base

data class DbData<T>(
    val data: T,
    val isExpired: Boolean = false
)