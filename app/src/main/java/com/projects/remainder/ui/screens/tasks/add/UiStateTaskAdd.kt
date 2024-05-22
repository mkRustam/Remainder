package com.projects.remainder.ui.screens.tasks.add

import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState

data class UiStateTaskAdd(
    private val builder: Builder
) : BaseUiState() {

    fun taskAdd(): Resource<EntityTask>? {
        return builder.taskAdd
    }

    class Builder : BaseUiState.Builder<UiStateTaskAdd> {
        var taskAdd: Resource<EntityTask>? = null
            private set

        fun taskAdd(result: Resource<EntityTask>) = apply { taskAdd = result}

        override fun build(): UiStateTaskAdd {
            return UiStateTaskAdd(this)
        }
    }
}