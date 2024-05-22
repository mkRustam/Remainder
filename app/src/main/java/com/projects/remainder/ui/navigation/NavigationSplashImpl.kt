package com.projects.remainder.ui.navigation

import com.projects.remainder.R
import com.projects.remainder.ui.screens.splash.NavigationSplash
import javax.inject.Inject

class NavigationSplashImpl @Inject constructor(
    private val router: Router
) : NavigationSplash {
    override fun main() {
        router.navigate(R.id.action_fragmentSplash_to_fragmentFlowMain)
    }

    override fun auth() {
        router.navigate(R.id.action_fragmentSplash_to_fragmentAuth)
    }
}