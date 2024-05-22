package com.projects.remainder.ui.screens.tasks.update

import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState

data class UiStateTaskUpdate(
    private val builder: Builder
) : BaseUiState() {

    fun task(): Resource<EntityTask>? {
        return builder.task
    }

    fun taskUpdated(): Resource<EntityTask>? {
        return builder.taskUpdated
    }

    class Builder {
        var task: Resource<EntityTask>? = null
            private set

        var taskUpdated: Resource<EntityTask>? = null
            private set

        fun task(result: Resource<EntityTask>) = apply { task = result}
        fun taskUpdated(result: Resource<EntityTask>) = apply { taskUpdated = result}

        fun build(): UiStateTaskUpdate {
            return UiStateTaskUpdate(this)
        }
    }
}