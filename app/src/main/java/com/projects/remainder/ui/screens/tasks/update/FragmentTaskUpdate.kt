package com.projects.remainder.ui.screens.tasks.update

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.R
import com.projects.remainder.ui.screens.tasks.base.FragmentTaskEditor
import com.projects.ui_components.common.NavbarView
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTaskUpdate : FragmentTaskEditor() {

    override var taskId: String? = null
        get() = argString(EXTRA_TASK_ID)

    private val viewModel: ViewModelTaskUpdate by viewModels()

    @Inject
    lateinit var navigation: NavigationTaskUpdate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.detail(taskId!!)
    }

    override fun init() {
        super.init()
        initNavBar(getString(R.string.screen_title_task_update))

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    override fun getNavbar(): NavbarView? {
        return binding?.navbar
    }

    private fun initTask(task: EntityTask?) {
        taskId = task?.id
        if(task != null) {
            binding?.fieldTaskTitle?.setValue(task.title)
            binding?.fieldTaskDate?.setSelectedDate(task.dateTime)
        }
        else toast(getString(R.string.task_not_found))
    }

    override fun getButtonText(): Int = R.string.button_save

    override fun submitForm(task: EntityTask) {
        lockScreen()
        viewModel.update(task)
    }

    private fun changeViewState(state: UiStateTaskUpdate) {
        if(state.task() != null) updateTaskState(state.task()!!)
        if(state.taskUpdated() != null) updateTaskUpdatedState(state.taskUpdated()!!)
    }

    private fun updateTaskState(task: Resource<EntityTask>) {
        when(task.status) {
            Status.LOADING -> {
                lockScreen()
            }
            Status.SUCCESS -> {
                unlockScreen()
                initTask(task.data)
            }
            Status.ERROR -> {
                unlockScreen()
            }
        }
    }

    private fun updateTaskUpdatedState(taskUpdated: Resource<EntityTask>) {
        when(taskUpdated.status) {
            Status.LOADING -> {
                lockScreen()
            }
            Status.SUCCESS -> {
                unlockScreen()
                navigation.finish()
            }
            Status.ERROR -> {
                unlockScreen()
            }
        }
    }

    companion object {

        private const val EXTRA_TASK_ID = "extra_task_id"

        fun getBundle(taskId: String): Bundle {
            return Bundle().apply {
                putString(EXTRA_TASK_ID, taskId)
            }
        }
    }
}