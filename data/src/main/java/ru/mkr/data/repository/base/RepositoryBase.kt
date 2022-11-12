package ru.mkr.data.repository.base

import kotlinx.coroutines.flow.*
import java.util.*

abstract class RepositoryBase {

    companion object {
        const val ERROR_UNKNOWN = "Unknown error"
    }
}