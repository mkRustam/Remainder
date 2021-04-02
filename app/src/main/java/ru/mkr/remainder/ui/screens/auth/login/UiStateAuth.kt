package ru.mkr.remainder.ui.screens.auth.login

import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

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