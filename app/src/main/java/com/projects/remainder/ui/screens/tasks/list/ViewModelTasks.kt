package com.projects.remainder.ui.screens.tasks.list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.Resource
import com.projects.domain.usecase.tasks.UseCaseTasksLoad
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import com.projects.remainder.ui.mappers.tasks.TaskUiMapper
import javax.inject.Inject

@HiltViewModel
class ViewModelTasks @Inject constructor(
    private val useCaseTasksLoad: UseCaseTasksLoad,
    @IoDispatcher private var dispatcher: CoroutineDispatcher,
    private var taskUiMapper: TaskUiMapper
) : BaseViewModel<UiStateTasks>(dispatcher) {

    fun loadTasks() {
        launch {
            useCaseTasksLoad.invoke(Unit).collect { data ->
                val state = UiStateTasks.Builder()
                state.tasks(Resource(data.status, taskUiMapper.map(data.data), data.message))
                emitScreenState(state.build())
            }
        }
    }

    override fun getInitScreenState(): UiStateTasks = UiStateTasks.Builder()
        .tasks(Resource.loading(null))
        .build()
}