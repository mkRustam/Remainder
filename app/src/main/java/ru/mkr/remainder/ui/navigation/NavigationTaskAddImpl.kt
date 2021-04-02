package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.ui.screens.tasks.add.NavigationTaskAdd
import javax.inject.Inject

class NavigationTaskAddImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTaskAdd {
    override fun finish() {
        router.back()
    }
}