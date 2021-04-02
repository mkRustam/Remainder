package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.auth.register.NavigationAuthRegister
import javax.inject.Inject

class NavigationAuthRegisterImpl @Inject constructor(
    private val router: Router
) : NavigationAuthRegister {
    override fun main() {
        router.navigate(R.id.action_fragmentAuthRegister_to_fragmentFlowMain)
    }
}