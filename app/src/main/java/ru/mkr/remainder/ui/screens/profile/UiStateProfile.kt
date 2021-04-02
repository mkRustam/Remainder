package ru.mkr.remainder.ui.screens.profile

import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

data class UiStateProfile(
    private val builder: Builder
) : BaseUiState() {

    fun logout(): Resource<Boolean>? {
        return builder.logout
    }

    class Builder {
        var logout: Resource<Boolean>? = null
            private set

        fun logout(result: Resource<Boolean>) = apply { logout = result}

        fun build(): UiStateProfile {
            return UiStateProfile(this)
        }
    }
}