package com.projects.remainder.ui.base

open class BaseUiState {
    interface Builder<T> {
        fun build(): T
    }
}