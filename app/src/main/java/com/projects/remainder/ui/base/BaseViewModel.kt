package com.projects.remainder.ui.base

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<SCREEN_STATE: BaseUiState>(
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val screenState = MutableStateFlow(getInitScreenState())
    private val screenEvents = MutableSharedFlow<BaseUiEvent>()

    protected abstract fun getInitScreenState(): SCREEN_STATE

    protected suspend fun emitScreenState(newState: SCREEN_STATE) {
        screenState.emit(newState)
    }

    suspend fun collectScreenState(handler: suspend (SCREEN_STATE) -> Unit) {
        screenState.collect(handler)
    }

    protected suspend fun emitScreenEvent(event: BaseUiEvent) {
        screenEvents.emit(event)
    }

    suspend fun collectScreenEvent(handler: suspend (BaseUiEvent) -> Unit) {
        screenEvents.collect(handler)
    }

    // Coroutines will survive configuration changes automatically if the work is started in the viewModelScope
    protected fun launch(
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(block = block, context = dispatcher)
}