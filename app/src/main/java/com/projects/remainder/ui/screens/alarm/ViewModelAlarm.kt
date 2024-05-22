package com.projects.remainder.ui.screens.alarm

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAlarm @Inject constructor(
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<StateAlarmUi>(dispatcher) {
    override fun getInitScreenState(): StateAlarmUi = StateAlarmUi()
}