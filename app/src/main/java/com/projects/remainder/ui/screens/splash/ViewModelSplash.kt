package com.projects.remainder.ui.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import com.projects.domain.usecase.auth.UseCaseUserAutologin
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSplash @Inject constructor(
    private var useCaseUserAutologin: UseCaseUserAutologin,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateSplash>(dispatcher) {

    fun autologin() {
        launch {
            val autologinResult = useCaseUserAutologin.invoke(Unit)
            val state = UiStateSplash.Builder()
            state.autologin(autologinResult)
            emitScreenState(state.build())
        }
    }

    override fun getInitScreenState(): UiStateSplash = UiStateSplash.Builder()
        .build()
}