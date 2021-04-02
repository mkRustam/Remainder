package ru.mkr.remainder.ui.base

open class BaseUiState {
    interface Builder<T> {
        fun build(): T
    }
}