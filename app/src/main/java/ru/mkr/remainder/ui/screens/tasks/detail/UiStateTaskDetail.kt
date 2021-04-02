package ru.mkr.remainder.ui.screens.tasks.detail

import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

data class UiStateTaskDetail(
    private val builder: Builder
) : BaseUiState() {

    fun detail(): Resource<EntityTask>? {
        return builder.detail
    }

    fun delete(): Resource<Unit>? {
        return builder.delete
    }

    class Builder {
        var detail: Resource<EntityTask>? = null
            private set

        var delete: Resource<Unit>? = null
            private set

        fun detail(result: Resource<EntityTask>) = apply { detail = result}
        fun delete(result: Resource<Unit>) = apply { delete = result }

        fun build(): UiStateTaskDetail {
            return UiStateTaskDetail(this)
        }
    }
}