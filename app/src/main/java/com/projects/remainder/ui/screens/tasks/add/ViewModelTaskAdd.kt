package com.projects.remainder.ui.screens.tasks.add

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.EntityTask
import com.projects.domain.usecase.tasks.UseCaseTaskCreate
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import com.projects.remainder.ui.mappers.utils.DateTimeUiMapper
import com.projects.remainder.utils.alarm.ManagerAlarmTask
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskAdd @Inject constructor(
    private var useCaseTaskCreate: UseCaseTaskCreate,
    private var managerAlarmTask: ManagerAlarmTask,
    private var dateTimeUiMapper: DateTimeUiMapper,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateTaskAdd>(dispatcher) {

    fun addTask(task: EntityTask) {
        launch {
            val result = useCaseTaskCreate.invoke(task)
            if(result.isSuccess() && result.data != null) managerAlarmTask.schedule(result.data!!)
            val state = UiStateTaskAdd.Builder()
            state.taskAdd(result)
            emitScreenState(state.build())
        }
    }

    fun convertDateToString(dateTime: Date): String = dateTimeUiMapper.map(dateTime)

    fun convertStringToDate(string: String): Date = dateTimeUiMapper.map(string)

    override fun getInitScreenState(): UiStateTaskAdd = UiStateTaskAdd.Builder()
        .build()
}