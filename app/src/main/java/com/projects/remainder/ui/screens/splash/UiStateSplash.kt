package com.projects.remainder.ui.screens.splash

import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.remainder.ui.base.BaseUiState

class UiStateSplash(
    private val builder: Builder
) : BaseUiState() {
    fun autologin(): Resource<EntityUser>? {
        return builder.autologin
    }

    class Builder {
        var autologin: Resource<EntityUser>? = null
            private set

        fun autologin(result: Resource<EntityUser>) = apply { autologin = result}

        fun build(): UiStateSplash {
            return UiStateSplash(this)
        }
    }
}