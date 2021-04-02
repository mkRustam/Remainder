package ru.mkr.remainder.ui.navigation

import android.os.Bundle
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.NavigationDeeplinks
import javax.inject.Inject

class NavigationDeeplinksImpl @Inject constructor(
    private val router: Router
) : NavigationDeeplinks {

    override fun alarm(args: Bundle) {
        router.navigate(R.id.fragmentTaskDetail)
    }
}