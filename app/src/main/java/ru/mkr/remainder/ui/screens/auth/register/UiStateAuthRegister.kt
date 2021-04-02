package ru.mkr.remainder.ui.screens.auth.register

import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

data class UiStateAuthRegister(
    private val builder: Builder
) : BaseUiState() {

    fun register(): Resource<EntityUser>? {
        return builder.register
    }

    class Builder {
        var register: Resource<EntityUser>? = null
            private set

        fun register(result: Resource<EntityUser>) = apply { register = result}

        fun build(): UiStateAuthRegister {
            return UiStateAuthRegister(this)
        }
    }
}