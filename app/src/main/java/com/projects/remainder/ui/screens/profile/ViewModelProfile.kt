package com.projects.remainder.ui.screens.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import com.projects.domain.usecase.auth.UseCaseUserLogout
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelProfile @Inject constructor(
    private var useCaseUserLogout: UseCaseUserLogout,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
): BaseViewModel<UiStateProfile>(dispatcher) {

    fun logout() {
        launch {
            val logoutResult = useCaseUserLogout.invoke(Unit)
            val state = UiStateProfile.Builder()
            state.logout(logoutResult)
            emitScreenState(state.build())
        }
    }

    override fun getInitScreenState(): UiStateProfile = UiStateProfile.Builder()
        .build()
}