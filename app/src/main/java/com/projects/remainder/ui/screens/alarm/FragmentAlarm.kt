package com.projects.remainder.ui.screens.alarm

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityTask
import com.projects.remainder.databinding.FragmentAlarmBinding
import com.projects.remainder.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAlarm : BaseScreen<FragmentAlarmBinding>() {

    private val viewModel: ViewModelAlarm by viewModels()

    @Inject
    lateinit var navigation: NavigationAlarm

    companion object {

        private const val TASK = "task"

        fun getBundle(task: EntityTask): Bundle {
            return Bundle(1).apply {
                putSerializable(TASK, task)
            }
        }
    }
}