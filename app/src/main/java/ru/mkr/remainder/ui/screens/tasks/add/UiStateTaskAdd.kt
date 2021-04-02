package ru.mkr.remainder.ui.screens.tasks.add

import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

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