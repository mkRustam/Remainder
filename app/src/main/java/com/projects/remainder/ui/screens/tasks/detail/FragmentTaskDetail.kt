package com.projects.remainder.ui.screens.tasks.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityTask
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.R
import com.projects.remainder.databinding.FragmentTaskDetailBinding
import com.projects.remainder.ui.base.BaseScreen
import com.projects.remainder.ui.screens.tasks.update.FragmentTaskUpdate
import com.projects.ui_components.common.NavbarView
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTaskDetail : BaseScreen<FragmentTaskDetailBinding>() {

    private val taskId: String?
        get() = argString(EXTRA_TASK_ID)

    private val viewModel: ViewModelTaskDetail by viewModels()

    @Inject
    lateinit var navigation: NavigationTaskDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.detail(taskId!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initNavBar(getString(R.string.screen_title_task_detail))
        initFields()
        initButtons()

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    override fun getNavbar(): NavbarView? {
        return binding?.navbar
    }

    private fun initTask(task: EntityTask?) {
        if(task != null) {
            binding?.fieldTaskTitle?.setValue(task.title)
            binding?.fieldTaskDate?.setSelectedDate(task.dateTime)
        }
        else toast(getString(R.string.task_not_found))
    }

    private fun initButtons() {
        binding?.btnEdit?.setOnClickListener {
            navigation.taskUpdate(FragmentTaskUpdate.getBundle(taskId!!))
        }
        binding?.btnDelete?.setOnClickListener {
            lockScreen()
//            viewModel.task.removeObservers(this@FragmentTaskDetail)
            viewModel.delete(taskId!!)
        }
    }

    private fun initFields() {
        binding?.fieldTaskTitle?.setTitle(getString(R.string.field_title_name))
        binding?.fieldTaskTitle?.setReadOnly()

        binding?.fieldTaskDate?.setTitle(getString(R.string.field_title_date))
        binding?.fieldTaskDate?.setReadOnly()
    }

    private fun changeViewState(state: UiStateTaskDetail) {
        if(state.delete() != null) updateDeleteState(state.delete()!!)
        if(state.detail() != null) updateDetailState(state.detail()!!)
    }

    private fun updateDetailState(detail: Resource<EntityTask>) {
        when(detail.status) {
            Status.LOADING -> {
                if(detail.data == null) lockScreen()
                else initTask(detail.data)
            }
            Status.SUCCESS -> {
                unlockScreen()
                initTask(detail.data)
            }
            Status.ERROR -> {
                unlockScreen()
            }
        }
    }

    private fun updateDeleteState(delete: Resource<Unit>) {
        when(delete.status) {
            Status.LOADING -> lockScreen()
            Status.SUCCESS -> {
                unlockScreen()
                navigation.back()
            }
            Status.ERROR -> {
                unlockScreen()
                toastNoEmpty(delete.message, errorUnavailable())
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