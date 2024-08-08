package com.projects.remainder.ui.screens.tasks.update

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.EntityTask
import com.projects.domain.usecase.tasks.UseCaseTaskDetail
import com.projects.domain.usecase.tasks.UseCaseTaskUpdate
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import com.projects.remainder.ui.mappers.utils.DateTimeUiMapper
import com.projects.remainder.utils.alarm.ManagerAlarmTask
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskUpdate @Inject constructor(
    private var useCaseTaskUpdate: UseCaseTaskUpdate,
    private var useCaseTaskDetail: UseCaseTaskDetail,
    private var managerAlarmTask: ManagerAlarmTask,
    private var dateTimeUiMapper: DateTimeUiMapper,
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

    fun convertDateToString(dateTime: Date): String = dateTimeUiMapper.map(dateTime)

    fun convertStringToDate(string: String): Date = dateTimeUiMapper.map(string)

    override fun getInitScreenState(): UiStateTaskUpdate = UiStateTaskUpdate.Builder()
        .build()
}