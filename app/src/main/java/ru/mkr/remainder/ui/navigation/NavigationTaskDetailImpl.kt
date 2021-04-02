package ru.mkr.remainder.ui.navigation

import android.os.Bundle
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.tasks.detail.NavigationTaskDetail
import javax.inject.Inject

class NavigationTaskDetailImpl @Inject constructor(
    private val router: RouterMainScreen
) : NavigationTaskDetail {
    override fun taskUpdate(args: Bundle) {
        router.navigate(R.id.fragmentTaskUpdate, args)
    }

    override fun back() {
        router.back()
    }
}