package ru.mkr.remainder.ui.screens.alarm

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.databinding.FragmentAlarmBinding
import ru.mkr.remainder.ui.base.BaseScreen
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