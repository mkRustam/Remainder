package com.projects.remainder.ui.navigation

import android.os.Bundle
import com.projects.remainder.R
import com.projects.remainder.ui.screens.tasks.detail.NavigationTaskDetail
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