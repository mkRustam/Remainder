package ru.mkr.remainder.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.base.BaseActivity
import ru.mkr.remainder.ui.base.IUiLocker
import ru.mkr.remainder.ui.navigation.Router
import ru.mkr.remainder.ui.views.UiLocker
import javax.inject.Inject

@AndroidEntryPoint
class ActivityMain : BaseActivity(), IUiLocker {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var screenLocker: UiLocker

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screenLocker = findViewById(R.id.screen_locker)
        router.injectController(findController())
    }

    override fun onResume() {
        super.onResume()
        router.injectController(findController())
    }

    override fun lock() {
        screenLocker.lock()
    }

    override fun unlock() {
        screenLocker.unlock()
    }

    override fun onBackPressed() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        val eventHandled = (fragment as IActivityCallback?)?.onActivityBackPressed() ?: false
        if(!eventHandled) super.onBackPressed()
    }

    private fun findController(): NavController {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_container_view) as NavHostFragment
        return navHostFragment.navController
    }
}