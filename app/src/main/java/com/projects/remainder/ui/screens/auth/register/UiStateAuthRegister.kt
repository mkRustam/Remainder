package com.projects.remainder.ui.screens.auth.register

import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState


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