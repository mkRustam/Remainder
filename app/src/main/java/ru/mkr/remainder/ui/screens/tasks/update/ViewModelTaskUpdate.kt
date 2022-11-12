package ru.mkr.remainder.ui.screens.tasks.update

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.usecase.tasks.UseCaseTaskDetail
import ru.mkr.domain.usecase.tasks.UseCaseTaskUpdate
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.remainder.ui.base.BaseViewModel
import ru.mkr.remainder.utils.alarm.ManagerAlarmTask
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskUpdate @Inject constructor(
    private var useCaseTaskUpdate: UseCaseTaskUpdate,
    private var useCaseTaskDetail: UseCaseTaskDetail,
    private var managerAlarmTask: ManagerAlarmTask,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateTaskUpdate>(dispatcher) {

    fun update(task: EntityTask) {
        launch {
            val result = useCaseTaskUpdate.invoke(task)
            if(result.isSuccess() && result.data != null) managerAlarmTask.schedule(result.data!!)
            val state = UiStateTaskUpdate.Builder()
            state.taskUpdated(result)
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