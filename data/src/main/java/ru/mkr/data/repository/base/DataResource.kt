package ru.mkr.data.repository.base

enum class DataResource {
    CACHED, // Only cached data allowed
    REFRESH, // Only remote data allowed
    COMMON // Get cached data if it hasn't expired, otherwise request remote data
}