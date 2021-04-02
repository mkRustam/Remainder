package ru.mkr.remainder.ui.screens.splash

import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.remainder.ui.base.BaseUiState

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