package ru.mkr.remainder.ui.screens.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import ru.mkr.domain.usecase.auth.UseCaseUserLogout
import ru.mkr.domain.utils.annotations.IoDispatcher
import ru.mkr.remainder.ui.base.BaseViewModel
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