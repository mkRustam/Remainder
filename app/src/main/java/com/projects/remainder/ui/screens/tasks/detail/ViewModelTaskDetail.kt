package com.projects.remainder.ui.screens.tasks.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.usecase.tasks.UseCaseTaskDelete
import com.projects.domain.usecase.tasks.UseCaseTaskDetail
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class ViewModelTaskDetail @Inject constructor(
    private var useCaseTaskDelete: UseCaseTaskDelete,
    private var useCaseTaskDetail: UseCaseTaskDetail,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateTaskDetail>(dispatcher) {

    // TODO Find better solution to fix "Task not found" after deleting task on detail page
    private var ignoringActionsCount = AtomicInteger()

    fun delete(id: String) {
        ignoringActionsCount.incrementAndGet()
        launch {
            val taskDeleteResult = useCaseTaskDelete.invoke(id)
            val state = UiStateTaskDetail.Builder()
            state.delete(taskDeleteResult)
            emitScreenState(state.build())
        }
    }

    fun detail(id: String) {
        launch {
            useCaseTaskDetail.invoke(id).collect {
                if(ignoringActionsCount.get() == 0) {
                    val state = UiStateTaskDetail.Builder()
                    state.detail(it)
                    emitScreenState(state.build())
                }
                else ignoringActionsCount.decrementAndGet()
            }
        }
    }

    override fun getInitScreenState(): UiStateTaskDetail = UiStateTaskDetail.Builder()
        .build()
}