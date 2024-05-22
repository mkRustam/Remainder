package com.projects.remainder.ui.navigation

import com.projects.remainder.R
import com.projects.remainder.ui.screens.auth.login.NavigationAuth
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