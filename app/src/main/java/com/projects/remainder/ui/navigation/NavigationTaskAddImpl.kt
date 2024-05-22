package com.projects.remainder.ui.navigation

import com.projects.remainder.ui.screens.tasks.add.NavigationTaskAdd
import javax.inject.Inject

class NavigationTaskAddImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTaskAdd {
    override fun finish() {
        router.back()
    }
}