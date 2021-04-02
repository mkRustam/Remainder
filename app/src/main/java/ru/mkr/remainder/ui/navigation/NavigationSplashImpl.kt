package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.splash.NavigationSplash
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