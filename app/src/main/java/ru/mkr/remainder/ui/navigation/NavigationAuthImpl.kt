package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.auth.login.NavigationAuth
import javax.inject.Inject

class NavigationAuthImpl @Inject constructor(
    private val router: Router
) : NavigationAuth {
    override fun register() {
        router.navigate(R.id.fragmentAuthRegister)
    }

    override fun main() {
        router.navigate(R.id.action_fragmentAuth_to_fragmentFlowMain)
    }
}