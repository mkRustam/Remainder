package com.projects.remainder.ui.screens.profile

import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState

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