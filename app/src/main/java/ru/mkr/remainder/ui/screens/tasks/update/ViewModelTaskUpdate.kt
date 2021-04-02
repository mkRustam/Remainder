package ru.mkr.remainder.ui.screens.tasks.update

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.usecase.tasks.UseCaseTaskDetail
import ru.mkr.domain.usecase.tasks.UseCaseTaskUpdate
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskUpdate @Inject constructor(
    private var useCaseTaskUpdate: UseCaseTaskUpdate,
    private var useCaseTaskDetail: UseCaseTaskDetail,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateTaskUpdate>(dispatcher) {

    fun update(task: EntityTask) {
        launch {
            val taskUpdateResult = useCaseTaskUpdate.invoke(task)
            val state = UiStateTaskUpdate.Builder()
            state.taskUpdated(taskUpdateResult)
            emitScreenState(state.build())
        }
    }

    fun detail(id: String) {
        launch {
            useCaseTaskDetail.invoke(id).collect {
                val state = UiStateTaskUpdate.Builder()
                state.task(it)
                emitScreenState(state.build())
            }
        }
    }

    override fun getInitScreenState(): UiStateTaskUpdate = UiStateTaskUpdate.Builder()
        .build()
}