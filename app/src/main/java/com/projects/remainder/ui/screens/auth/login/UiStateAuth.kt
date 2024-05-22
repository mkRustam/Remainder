package com.projects.remainder.ui.screens.auth.login

import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState

data class UiStateAuth(
    private val builder: Builder
) : BaseUiState() {

    fun login(): Resource<EntityUser>? {
        return builder.login
    }

    class Builder {
        var login: Resource<EntityUser>? = null
            private set

        fun login(result: Resource<EntityUser>) = apply { login = result}

        fun build(): UiStateAuth {
            return UiStateAuth(this)
        }
    }
}