package com.projects.remainder.ui.screens.auth.register

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import com.projects.domain.usecase.auth.UseCaseUserRegister
import com.projects.domain.utils.annotations.IoDispatcher
import com.projects.remainder.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAuthRegister @Inject constructor(
    private var useCaseUserRegister: UseCaseUserRegister,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
): BaseViewModel<UiStateAuthRegister>(dispatcher) {

    fun register(email: String, pass: String) {
        launch {
            val registerResult = useCaseUserRegister.invoke(UseCaseUserRegister.Params(email, pass))
            val state = UiStateAuthRegister.Builder()
            state.register(registerResult)
            emitScreenState(state.build())
        }
    }

    override fun getInitScreenState(): UiStateAuthRegister = UiStateAuthRegister.Builder()
        .build()
}