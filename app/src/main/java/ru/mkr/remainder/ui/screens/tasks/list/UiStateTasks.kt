package ru.mkr.remainder.ui.screens.tasks.list

import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState
import ru.mkr.remainder.ui.entity.tasks.TaskUiEntity

data class UiStateTasks(
    private val builder: Builder
) : BaseUiState() {

    fun tasks(): Resource<List<TaskUiEntity>>? {
        return builder.tasks
    }

    class Builder {
        var tasks: Resource<List<TaskUiEntity>>? = null
            private set

        fun tasks(result: Resource<List<TaskUiEntity>>) = apply { tasks = result}

        fun build(): UiStateTasks {
            return UiStateTasks(this)
        }
    }
}