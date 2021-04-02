package ru.mkr.remainder.ui.screens.alarm

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.remainder.databinding.FragmentAlarmBinding
import ru.mkr.remainder.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAlarm : BaseScreen<FragmentAlarmBinding>() {

    private val viewModel: ViewModelAlarm by viewModels()

    @Inject
    lateinit var navigation: NavigationAlarm
}