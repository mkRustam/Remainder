package ru.mkr.remainder.ui.screens.tasks.add

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.usecase.tasks.UseCaseTaskCreate
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskAdd @Inject constructor(
    private var useCaseTaskCreate: UseCaseTaskCreate,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateTaskAdd>(dispatcher) {

    fun addTask(task: EntityTask) {
        launch {
            val taskCreateResult = useCaseTaskCreate.invoke(task)
            val state = UiStateTaskAdd.Builder()
            state.taskAdd(taskCreateResult)
            emitScreenState(state.build())
        }
    }

    override fun getInitScreenState(): UiStateTaskAdd = UiStateTaskAdd.Builder()
        .build()
}