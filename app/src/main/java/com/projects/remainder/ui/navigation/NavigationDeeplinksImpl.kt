package com.projects.remainder.ui.navigation

import android.os.Bundle
import com.projects.remainder.R
import com.projects.remainder.ui.screens.NavigationDeeplinks
import javax.inject.Inject

class NavigationDeeplinksImpl @Inject constructor(
    private val router: Router
) : NavigationDeeplinks {

    override fun alarm(args: Bundle) {
        router.navigate(R.id.fragmentTaskDetail)
    }
}