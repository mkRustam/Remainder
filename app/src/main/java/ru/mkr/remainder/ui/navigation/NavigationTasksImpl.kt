package ru.mkr.remainder.ui.navigation

import android.os.Bundle
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.tasks.list.NavigationTasks
import javax.inject.Inject

class NavigationTasksImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTasks {
    override fun taskDetail(args: Bundle) {
        router.navigate(R.id.fragmentTaskDetail, args)
    }

    override fun taskAdd() {
        router.navigate(R.id.fragmentTaskAdd)
    }
}