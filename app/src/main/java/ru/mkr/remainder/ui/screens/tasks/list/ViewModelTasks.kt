package ru.mkr.remainder.ui.screens.tasks.list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.usecase.tasks.UseCaseTasksLoad
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.remainder.ui.base.BaseViewModel
import ru.mkr.remainder.ui.mappers.tasks.TaskUiMapper
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