package com.projects.remainder.ui.navigation

import com.projects.remainder.ui.screens.tasks.update.NavigationTaskUpdate
import javax.inject.Inject

class NavigationTaskUpdateImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTaskUpdate {
    override fun finish() {
        router.back()
    }
}