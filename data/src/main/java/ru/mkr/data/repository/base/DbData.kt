package ru.mkr.data.repository.base

data class DbData<T>(
    val data: T,
    val isExpired: Boolean = false
)