package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.ui.screens.tasks.update.NavigationTaskUpdate
import javax.inject.Inject

class NavigationTaskUpdateImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTaskUpdate {
    override fun finish() {
        router.back()
    }
}