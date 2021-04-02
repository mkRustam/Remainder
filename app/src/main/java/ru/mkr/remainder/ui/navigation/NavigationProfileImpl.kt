package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.profile.NavigationProfile
import javax.inject.Inject

class NavigationProfileImpl @Inject constructor(
    private val router: Router,
    private val routerMainScreen: RouterMainScreen
) : NavigationProfile {
    override fun logout() {
        router.resetNavigation()
        router.navigate(R.id.action_global_fragmentAuth)
    }
}