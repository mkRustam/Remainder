package com.projects.remainder.ui.screens.auth.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.usecase.auth.UseCaseUserLogin
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(
    private var useCaseUserLogin: UseCaseUserLogin,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<UiStateAuth>(dispatcher) {

    fun login(email: String, pass: String) {
        launch {
            val loginResult = useCaseUserLogin.invoke(UseCaseUserLogin.Params(email, pass))
            val state = UiStateAuth.Builder()
            state.login(loginResult)
            emitScreenState(state.build())
        }
    }

    override fun getInitScreenState(): UiStateAuth = UiStateAuth.Builder()
        .build()
}